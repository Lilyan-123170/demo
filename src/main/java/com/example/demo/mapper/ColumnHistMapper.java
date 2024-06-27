package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Column;
import com.example.demo.entity.ColumnHist;
import com.example.demo.request.ColumnHistRequest;
import com.example.demo.request.ColumnRequest;
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
 * @author Liyimei
 * @since 2024-05-26
 */
@Mapper
public interface ColumnHistMapper extends BaseMapper<ColumnHist> {

    void insertPlanUploadColumnHist(@Param("columnHistRequest") List<ColumnHistRequest> columnHistRequest,@Param("planManageRequest") PlanManageRequest planManageRequest);



//    void insertColumn(String id, ColumnRequest columnRequest);
//    void updateColumn(String id, ColumnRequest columnRequest);
//    void deleteColumn(String id, ColumnRequest columnRequest);

}
