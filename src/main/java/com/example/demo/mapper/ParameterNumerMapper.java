package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface ParameterNumerMapper extends BaseMapper<ParameterNumerRequest> {
    void insertParameterNumerList(@Param("parameterNumerRequest") List<ParameterNumerRequest> parameterNumerRequest);
    void deleteParameterNumer(@Param("parId") Long parId);

    List<ParameterNumerRequest> selectParameterNumer(@Param("parId") Long parId);

}
