package com.example.demo.service;

import com.example.demo.bo.ParameterBO;
import com.example.demo.entity.Column;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.request.ColumnRequest;
import com.example.demo.request.ParameterRequest;
import com.example.demo.request.PlanManageRequest;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuYunhan
 * @since 2024-05-26
 */
public interface IColumnService extends IService<Column> {
    List<Double> evaluate(Long couId, List<ParameterBO> parameterBOList, int n);

    Map<String,Object> insertColumn(Map<String,Object> map);
    Map updateColumn(Map<String,Object> map);
    void deleteColumn(Map<String,Object> map);
    Map<String,Object> selectColumn(PlanManageRequest planManageRequest);
    Column getColumn(Long columnId);
}
