package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Parameter;
import com.example.demo.entity.ParameterHist;
import com.example.demo.request.ColumnHistRequest;
import com.example.demo.request.ParameterHistRequest;
import com.example.demo.request.ParameterRequest;
import com.example.demo.request.PlanManageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liyimei
 * @since 2024-05-26
 */
@Mapper
public interface ParameterHistMapper extends BaseMapper<ParameterHist> {
    void insertPlanUploadParaHistSole(@Param("parameterHistRequest") ParameterHistRequest parameterHistRequest, @Param("planManageRequest") PlanManageRequest planManageRequest);
    void insertPlanUploadParaHistDouble(@Param("parameterHistRequest") ParameterHistRequest parameterHistRequest, @Param("planManageRequest") PlanManageRequest planManageRequest);

//    void deleteParameter(String id, List<ParameterRequest> parameterRequest);
}
