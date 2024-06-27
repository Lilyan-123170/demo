package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Column;
import com.example.demo.entity.Parameter;
import com.example.demo.entity.PlanManage;
import com.example.demo.mapper.*;
import com.example.demo.request.*;
import com.example.demo.service.IPlanManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liyimei
 * @since 2024-05-26
 */
@Service
public class PlanManageServiceImpl extends ServiceImpl<PlanManageMapper, PlanManage> implements IPlanManageService {
    @Autowired
    private ColumnHistMapper columnHistMapper;
    @Autowired
    private ParameterHistMapper parameterHistMapper;
    @Autowired
    private ColumnMapper columnMapper;
    @Autowired
    private ParameterMapper parameterMapper;
    @Autowired
    private PlanManageMapper planManageMapper;

    @Autowired
    private ParameterNumerHistMapper parameterNumerHistMapper;
    @Autowired
    private ParameterNumerMapper parameterNumerMapper;




    @Override
    public void planHistRecord(Map<String,Object> map) {
        List<ParameterHistRequest> parameterHistRequest = JSON.parseArray(JSON.toJSONString(map.get("parameter")), ParameterHistRequest.class);
        List<ColumnHistRequest> columnHistRequest = JSON.parseArray(JSON.toJSONString(map.get("column")), ColumnHistRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);
        if (planManageRequest.getPlanState().equals("02")){
            planManageMapper.updatePlanUploadState(planManageRequest);
        }else if(planManageRequest.getPlanState().equals("06")){
            planManageMapper.updatePlanModiUploadState(planManageRequest);
            planManageMapper.updatePlanUploadState(planManageRequest);
        }
        columnHistMapper.insertPlanUploadColumnHist(columnHistRequest,planManageRequest);
        parameterHistRequest.forEach(p->{if (p.getType().equals("0")){
            parameterHistMapper.insertPlanUploadParaHistSole(p,planManageRequest);
        }
        else if(p.getType().equals("1")){
            parameterHistMapper.insertPlanUploadParaHistDouble(p,planManageRequest);
            List<ParameterNumerHistRequest> parameterNumerHistRequest = JSON.parseArray(JSON.toJSONString(p.getParVal()), ParameterNumerHistRequest.class);
            p.setParVal(parameterNumerHistRequest);
            parameterNumerHistRequest.forEach(c->c.setParId(p.getParId()));
            parameterNumerHistMapper.insertParameterNumerHistList(parameterNumerHistRequest);

        }});

    }

    @Override
    public void planCreate(PlanManageRequest planManageRequest) {
        planManageRequest.setPlanState("02");
        planManageMapper.planCreate(planManageRequest);
        planManageRequest.setPlanCode(createPlanCode(String.valueOf(planManageRequest.getPlanId())));
        planManageMapper.updatePlanCode(planManageRequest);
        System.out.println(planManageRequest);
    }

    public static String createPlanCode(String planId) {

        // 将名称转换为字节数组
        byte[] nameBytes = planId.getBytes(StandardCharsets.UTF_8);
        // 生成名称基UUID
        UUID nameBasedUUID = UUID.nameUUIDFromBytes(nameBytes);
        // 打印生成的UUID
        return String.valueOf(nameBasedUUID).replace("-","");
    }

    @Override
    public Map planEdit(Map map) {
        List<ParameterRequest> parameterRequest = JSON.parseArray(JSON.toJSONString(map.get("parameter")), ParameterRequest.class);
        List<ColumnRequest> columnRequest = JSON.parseArray(JSON.toJSONString(map.get("column")), ColumnRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);

        planManageMapper.planEditFirstState(planManageRequest);

        planManageMapper.planEditSecondInsert(planManageRequest);


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
        planManageRequest.setPlanState("06");
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("parameter",parameterRequest);
        resultMap.put("column",columnRequest);
        resultMap.put("planInfo",planManageRequest);
        return resultMap;
    }



    @Override
    public Map planCopy(Map map) {
        List<ParameterRequest> parameterRequest = JSON.parseArray(JSON.toJSONString(map.get("parameter")), ParameterRequest.class);
        List<ColumnRequest> columnRequest = JSON.parseArray(JSON.toJSONString(map.get("column")), ColumnRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);
        planManageRequest.setPlanState("02");
        planManageMapper.planCreate(planManageRequest);
        planManageRequest.setPlanCode(createPlanCode(String.valueOf(planManageRequest.getPlanId())));
        planManageMapper.updatePlanCode(planManageRequest);

        System.out.println(planManageRequest);
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

        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("parameter",parameterRequest);
        resultMap.put("column",columnRequest);
        resultMap.put("planInfo",planManageRequest);
        return resultMap;


    }


    @Override
    public void planDelete(Map<String,Object> map) {
        System.out.println(map);
        List<ParameterRequest> parameterRequest = JSON.parseArray(JSON.toJSONString(map.get("parameter")), ParameterRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);
        System.out.println(planManageRequest);
        if (planManageRequest.getPlanState().equals("01")){
            planManageMapper.planDeleteUpload(planManageRequest);
        }else if(planManageRequest.getPlanState().equals("02")){
            planManageMapper.planDeleteDraft(planManageRequest);
            columnMapper.deleteColumnDraw(planManageRequest.getPlanId());
            parameterMapper.deleteParameterDraw(planManageRequest.getPlanId());
            parameterRequest.forEach(p->{if (p.getType().equals("1")){
                parameterNumerMapper.deleteParameterNumer(p.getParId());
            }
        });}else if(planManageRequest.getPlanState().equals("06")){
            planManageMapper.planDeleteDraft(planManageRequest);
            planManageMapper.planDeleteEditDraft(planManageRequest);
            columnMapper.deleteColumnDraw(planManageRequest.getPlanId());
            parameterMapper.deleteParameterDraw(planManageRequest.getPlanId());
            parameterRequest.forEach(p->{if (p.getType().equals("1")){
                parameterNumerMapper.deleteParameterNumer(p.getParId());
            }
        });
    }}

    @Override
    public List planCheck(PlanManageRequest planManageRequest) {
        List<PlanManageRequest> planManageRequestList= planManageMapper.planCheck(planManageRequest);
        System.out.println(planManageRequestList);
        List resultList = new ArrayList<>();
        planManageRequestList.forEach(p->{
            List<Parameter> parameterList = parameterMapper.selectParameter(p.getPlanId());
            parameterList.forEach(c->{if (c.getType().equals("1")){
                c.setParVal(parameterNumerMapper.selectParameterNumer(c.getParId()));}});
            List<ColumnRequest> columnList=columnMapper.selectColumn(planManageRequest.getPlanId());
            Map<String, Object> map=new HashMap<>();

            map.put("parameter",parameterList);
            map.put("column",columnList);
            map.put("planInfo",p);
            resultList.add(map);
        });
        return resultList;


    }


}
















