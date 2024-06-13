package com.example.demo.service.impl;

import com.example.demo.bo.ColumnBO;
import com.example.demo.bo.ParameterBO;
import com.example.demo.entity.Column;
import com.example.demo.mapper.ColumnMapper;
import com.example.demo.service.IColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.EntityToBoConverter;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author WuYunhan
 * @since 2024-05-23
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements IColumnService {
    @Autowired
    private ColumnMapper columnMapper;
    /*
    计算处理好的表达式
     */
    public Double expEval(Long colId, String calExp, List<ParameterBO> parameterBOList, int n){
        Set<String> variableSet = extractVariables(calExp);
        Map<String,Double> variablesMap = variablesMap(calExp,parameterBOList,n);
        ExpressionBuilder expressionBuilder = new ExpressionBuilder(calExp);
        Expression expression = expressionBuilder.variables(variableSet).build();
        expression.setVariables(variablesMap);
        double res = expression.evaluate();
        parameterBOList.stream().filter(parameterBO -> colId.equals( parameterBO.getCallColId()))
                .forEach(parameterBO -> parameterBO.setValue(parameterBO.getValue() + "," + String.valueOf(res)));
        return res;
    }

    /*
    正则匹配表达式中的变量
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
        Map<String,Double> varMap = parameterBOList.stream().filter(bo->variables.contains(bo.getParaId()))
                .collect(Collectors.toMap(bo -> "p" + bo.getParaId(),
                bo -> {List<String> vars = Arrays.asList(bo.getValue().split(","));
                        return Double.valueOf(vars.get(n < vars.size() ? n : vars.size() - 1));}));

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
        /*初始表达式
        计算子公式，返回计算结果，写入
        */
        List<Double> res = new ArrayList<>();
        for(int i = 0 ; i <= n ; i++) {
            String subExp = subExpEval(colId,columnBO.getCalSeq(), parameterBOList, i);
            double tempRes = expEval(colId, subExp, parameterBOList, i);
            res.add(tempRes);
        }
        return res;
    }

    @Override
    public Column getColumn(Long columnId) {
        return columnMapper.selectByColumnId(columnId);
    }
}
