package com.example.demo.mapper;

import com.example.demo.entity.Column;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WuYunhan
 * @since 2024-05-26
 */
@Mapper
public interface ColumnMapper extends BaseMapper<Column> {
    @Select("SELECT * FROM `COLUMN` WHERE COL_ID=#{colId}")
    Column selectByColumnId(@Param("colId") Long colId);
}
