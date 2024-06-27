package com.example.demo.mapper;

import com.example.demo.entity.Parameter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @author WuYunhan
 * @since 2024-05-26
 */
@Mapper
public interface ParameterMapper extends BaseMapper<Parameter> {
    void insertParameter(@Param("parameterRequest") ParameterRequest parameterRequest,@Param("planManageRequest") PlanManageRequest planManageRequest);
    void insertParameterList(@Param("parameterRequest") ParameterRequest parameterRequest,@Param("planManageRequest") PlanManageRequest planManageRequest);
    void updateParameter(@Param("parameterRequest") ParameterRequest parameterRequest);
    void updateParameterSerial(@Param("parameterRequest") ParameterRequest parameterRequest);
    void deleteParameter(@Param("parId") Long parId);
    void deleteParameterDraw(@Param("planId") Long planId);

    List<Parameter> selectParameter(@Param("planId") Long planId);

//    void deleteParameter(String id, List<ParameterRequest> parameterRequest);
}
