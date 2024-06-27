package com.example.demo.mapper;

import com.example.demo.entity.Column;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.request.ColumnRequest;
import com.example.demo.request.ParameterRequest;
import com.example.demo.request.PlanManageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface ColumnMapper extends BaseMapper<Column> {
    @Select("SELECT * FROM `COLUMN` WHERE COL_ID=#{colId}")
    Column selectByColumnId(@Param("colId") Long colId);
    void insertColumn(@Param("columnRequest") List<ColumnRequest> columnRequest,@Param("planManageRequest") PlanManageRequest planManageRequest);
    void updateFormulaColumn(@Param("columnRequest") List<ColumnRequest> columnRequest);

    void updateColumn(@Param("columnRequest") List<ColumnRequest> columnRequest);

    void deleteColumn(@Param("columnRequest") List<ColumnRequest> columnRequest);
    void deleteColumnImport(@Param("colId") Long colId);

    List<ColumnRequest> selectColumn(@Param("planId") Long planId);

    void deleteColumnDraw(@Param("planId") Long planId);


//    void insertColumn(String id, ColumnRequest columnRequest);
//    void updateColumn(String id, ColumnRequest columnRequest);
//    void deleteColumn(String id, ColumnRequest columnRequest);

}
