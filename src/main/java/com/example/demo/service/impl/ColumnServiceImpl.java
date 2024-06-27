package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.bo.ColumnBO;
import com.example.demo.bo.ParameterBO;
import com.example.demo.entity.Column;
import com.example.demo.entity.Parameter;
import com.example.demo.entity.ParameterNumer;
import com.example.demo.mapper.ColumnMapper;
import com.example.demo.mapper.ParameterMapper;
import com.example.demo.mapper.ParameterNumerMapper;
import com.example.demo.request.ColumnRequest;
import com.example.demo.request.PlanManageRequest;
import com.example.demo.request.PlanManageRequest;
import com.example.demo.request.ParameterNumerRequest;
import com.example.demo.request.ParameterRequest;
import com.example.demo.service.IColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.EntityToBoConverter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liyimei
 * @since 2024-05-23
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements IColumnService {
    @Autowired
    private ColumnMapper columnMapper;
    @Autowired
    private ParameterMapper parameterMapper;
    @Autowired
    private ParameterNumerMapper parameterNumerMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String,Object> insertColumn(Map<String,Object> map) {
        List<ParameterRequest> parameterRequest = JSON.parseArray(JSON.toJSONString(map.get("parameter")), ParameterRequest.class);
        List<ColumnRequest> columnRequest = JSON.parseArray(JSON.toJSONString(map.get("column")), ColumnRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);
        parameterRequest.forEach(p->{if (p.getType().equals("0")){
            parameterMapper.insertParameter(p,planManageRequest);
        }
        else if(p.getType().equals("1")){
            parameterMapper.insertParameterList(p,planManageRequest);
            List<ParameterNumerRequest> parameterNumerRequest = JSON.parseArray(JSON.toJSONString(p.getParVal()), ParameterNumerRequest.class);
            p.setParVal(parameterNumerRequest);
            parameterNumerRequest.forEach(c->c.setParId(p.getParId()));
            parameterNumerMapper.insertParameterNumerList(parameterNumerRequest);

        }
        });

        columnRequest.forEach(c->{
            parameterRequest.stream().forEach(p->c.setCalSeq(c.getCalSeq().replace(p.getParName(),"p"+p.getParId())));
        });

        columnMapper.insertColumn(columnRequest,planManageRequest);
        columnRequest.forEach(c->{
            columnRequest.stream().forEach(p->c.setCalSeq(c.getCalSeq().replace(p.getColName(),"c"+p.getColId())));
        });
        columnMapper.updateFormulaColumn(columnRequest);

        map.put("parameter",parameterRequest);
        map.put("column",columnRequest);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map updateColumn(Map<String,Object> map) {
        List<ParameterRequest> parameterRequest = JSON.parseArray(JSON.toJSONString(map.get("parameter")), ParameterRequest.class);
        List<ColumnRequest> columnRequest = JSON.parseArray(JSON.toJSONString(map.get("column")), ColumnRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);
        parameterRequest.forEach(p->{if (p.getType().equals("0")){
            parameterMapper.updateParameter(p);
            parameterNumerMapper.deleteParameterNumer(p.getParId());
        }
        else if(p.getType().equals("1")){
            parameterMapper.updateParameterSerial(p);
            parameterNumerMapper.deleteParameterNumer(p.getParId());

            List<ParameterNumerRequest> parameterNumerRequest = JSON.parseArray(JSON.toJSONString(p.getParVal()), ParameterNumerRequest.class);
            parameterNumerRequest.forEach(c->c.setParId(p.getParId()));
            p.setParVal(parameterNumerRequest);
            parameterNumerMapper.insertParameterNumerList(parameterNumerRequest);}
        });

        columnRequest.forEach(c->{
            parameterRequest.stream().forEach(p->c.setCalSeq(c.getCalSeq().replace(p.getParName(),"p"+p.getParId())));
            columnRequest.stream().forEach(p->c.setCalSeq(c.getCalSeq().replace(p.getColName(),"c"+p.getColId())));
        });

        columnMapper.updateColumn(columnRequest);
        Map<String, Object> resultMap=new HashMap<>();

        resultMap.put("parameter",parameterRequest);
        resultMap.put("column",columnRequest);
        resultMap.put("planInfo",planManageRequest);
        return resultMap;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteColumn(Map<String,Object> map) {
        List<ColumnRequest> columnRequest = JSON.parseArray(JSON.toJSONString(map.get("column")), ColumnRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);
        List<ColumnRequest> columnResult=columnMapper.selectColumn(planManageRequest.getPlanId());
        columnMapper.deleteColumn(columnRequest);
        columnRequest.forEach(p->{columnResult.forEach(c->{
            System.out.println("c"+p.getColId());
            if(c.getCalSeq().contains("c"+p.getColId())){
                System.out.println("c.getCalSeq()");
                System.out.println(c.getCalSeq());
                System.out.println("c.getCalSeq()");
                columnMapper.deleteColumnImport(c.getColId());}
        });
            });
        }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String,Object> selectColumn(PlanManageRequest planManageRequest) {
        System.out.println(planManageRequest.getPlanId());
        List<Parameter> parameterList = parameterMapper.selectParameter(planManageRequest.getPlanId());
        System.out.println(parameterList);
        parameterList.forEach(p->{if (p.getType().equals("1")){
            System.out.println(parameterNumerMapper.selectParameterNumer(p.getParId()));
                    p.setParVal(parameterNumerMapper.selectParameterNumer(p.getParId()));}});
        List<ColumnRequest> columnList=columnMapper.selectColumn(planManageRequest.getPlanId());
        Map<String, Object> map=new HashMap<>();

        map.put("parameter",parameterList);
        map.put("column",columnList);
        map.put("planInfo",planManageRequest);
        return map;
    }







    /*
    计算处理好的表达式(只有运算符和参数)
     */
    public Double expEval(Long colId, String calExp, List<ParameterBO> parameterBOList, int n){
        Set<String> variableSet = extractVariables(calExp);      //!!!公式里只有p,筛选出了p,对应的值，在parameterBOList（前端给）里都有，但如果筛选出的是c，此处需到常量表取数
        Map<String,Double> variablesMap = variablesMap(calExp,parameterBOList,n);
        ExpressionBuilder expressionBuilder = new ExpressionBuilder(calExp);
        Expression expression = expressionBuilder.variables(variableSet).build();
        //parameterBOList.stream().filter(parameter -> paraId.contains(parameter.getParaId())).forEach(paraBO -> expression.setVariable("p" + paraBO.getParaId(),Double.valueOf(paraBO.getValue())));
        expression.setVariables(variablesMap);
        double res = expression.evaluate();
        parameterBOList.stream().filter(parameterBO -> colId.equals( parameterBO.getCallColId()))
                .forEach(parameterBO -> parameterBO.setValue(parameterBO.getValue() + "," + String.valueOf(res)));
        return res;
    }

    /*
    正则匹配表达式中的变量      变量筛选
     */

    public Set<String> extractVariables(String expression){
        Set<String> variables = new HashSet<String>();
        String regex = "p(\\d*?)(?=\\D|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            String param = matcher.group();
            variables.add(param);
        }
        return variables;
    }

    /*
    处理入参，将listBo转换为string-double的map，用于exp4j设置参数
    n为特殊入参，表示数列的第n个，默认为0
     */

    public Map<String,Double> variablesMap(String calExp,List<ParameterBO> parameterBOList,int n){
        /*
        流处理bolist，筛选公式中存在的参数
        将id和value写入map
        数列型的value根据下标n写入
         */

        Set<Long> variables = extractVariables(calExp).stream().map(s -> Long.valueOf(s.substring(1)))
                .collect(Collectors.toSet());
        System.out.println("variables1");
        System.out.println(variables);
        System.out.println("variables2");
        Map<String,Double> varMap = parameterBOList.stream().filter(bo->variables.contains(bo.getParaId()))
                .collect(Collectors.toMap(bo -> "p" + bo.getParaId(),
                bo -> {List<String> vars = Arrays.asList(bo.getValue().split(","));
                        return Double.valueOf(vars.get(n < vars.size() ? n : vars.size() - 1));}));
        System.out.println("varMap1");
        System.out.println(varMap);
        System.out.println("varMap2");
        return varMap;
    }

    /*
    处理子公式
     */
    public String subExpEval(Long colId, String expression, List<ParameterBO> parameterBOList, int n){
        StringBuffer sb = new StringBuffer();
        EntityToBoConverter entityToBoConverter = new EntityToBoConverter();
        String regex = "c(\\d*?)(?=\\D|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            String subCol = matcher.group();
            Long subColId = Long.valueOf(subCol.substring(1));
            Column column = getColumn(subColId);
            ColumnBO columnBO = EntityToBoConverter.convert(column,ColumnBO.class);
            System.out.println(columnBO);
            String subExp = columnBO.getCalSeq();
            if(subExp.contains("c")){
                subExp = subExpEval(subColId,subExp,parameterBOList,n);
            }
            double subRes = expEval(subColId,subExp,parameterBOList,n);
            matcher.appendReplacement(sb,String.valueOf(subRes));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Override
    public List<Double> evaluate(Long colId,List<ParameterBO> parameterBOList,int n){
        ColumnBO columnBO = EntityToBoConverter.convert(getColumn(colId),ColumnBO.class);
        System.out.println("------");
        System.out.println(columnBO);
        /*初始表达式
        计算子公式，返回计算结果，写入
        */
        List<Double> res = new ArrayList<>();
        for(int i = 0 ; i <= n ; i++) {
            String subExp = subExpEval(colId,columnBO.getCalSeq(), parameterBOList, i);
            System.out.println("subExp");
            System.out.println(subExp);
            double tempRes = expEval(colId, subExp, parameterBOList, i);
            System.out.println("tempRes");
            System.out.println(tempRes);
            res.add(tempRes);
        }
        return res;
    }

    @Override
    public Column getColumn(Long columnId) {
        return columnMapper.selectByColumnId(columnId);
    }
}
