package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Column;
import com.example.demo.entity.Parameter;
import com.example.demo.mapper.ColumnMapper;
import com.example.demo.mapper.ParameterMapper;
import com.example.demo.mapper.ParameterNumerMapper;
import com.example.demo.request.ColumnRequest;
import com.example.demo.request.ParameterRequest;
import com.example.demo.request.PlanManageRequest;
import com.example.demo.service.IParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liyimei
 * @since 2024-05-26
 */
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements IParameterService {
    @Autowired
    private ColumnMapper columnMapper;
    @Autowired
    private ParameterMapper parameterMapper;
    @Autowired
    private ParameterNumerMapper parameterNumerMapper;

    @Override
    public void deleteParameter(Map<String,Object> map) {
        List<ParameterRequest> parameterRequest = JSON.parseArray(JSON.toJSONString(map.get("parameter")), ParameterRequest.class);
        PlanManageRequest planManageRequest = JSON.parseObject(JSON.toJSONString(map.get("planInfo")), PlanManageRequest.class);
        List<ColumnRequest> columnRequest=columnMapper.selectColumn(planManageRequest.getPlanId());
        parameterRequest.forEach(p->{
            parameterMapper.deleteParameter(p.getParId());
            parameterNumerMapper.deleteParameterNumer(p.getParId());

        });


        List<ColumnRequest> columnRequestRaw= columnRequest;
        parameterRequest.forEach(c->{
            columnRequest.stream().filter(p->p.getCalSeq().contains("p"+c.getParId()));
        });
        columnMapper.deleteColumn(columnRequest);
        columnRequest.forEach(c->{
            columnRequestRaw.stream().filter(p->p.getCalSeq().contains("c"+c.getColId()));
        });
        columnMapper.deleteColumn(columnRequestRaw);}
}
