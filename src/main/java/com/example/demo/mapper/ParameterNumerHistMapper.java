package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.request.ParameterNumerHistRequest;
import com.example.demo.request.ParameterNumerRequest;
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
public interface ParameterNumerHistMapper extends BaseMapper<ParameterNumerHistRequest> {
    void insertParameterNumerHistList(@Param("parameterNumerHistRequest") List<ParameterNumerHistRequest> parameterNumerHistRequest);



}
