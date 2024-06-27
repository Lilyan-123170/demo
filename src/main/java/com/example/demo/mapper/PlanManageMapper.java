package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Parameter;
import com.example.demo.entity.PlanManage;
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
public interface PlanManageMapper extends BaseMapper<PlanManage> {
    void updatePlanUploadState(@Param("planManageRequest") PlanManageRequest planManageRequest);
    void updatePlanModiUploadState(@Param("planManageRequest") PlanManageRequest planManageRequest);

    void planCreate(@Param("planManageRequest") PlanManageRequest planManageRequest);

    void planEditFirstState(@Param("planManageRequest") PlanManageRequest planManageRequest);

    void planEditSecondInsert(@Param("planManageRequest") PlanManageRequest planManageRequest);

    void planDeleteUpload(@Param("planManageRequest") PlanManageRequest planManageRequest);
    void planDeleteDraft(@Param("planManageRequest") PlanManageRequest planManageRequest);
    void planDeleteEditOrigi(@Param("planManageRequest") PlanManageRequest planManageRequest);
    void planDeleteEditDraft(@Param("planManageRequest") PlanManageRequest planManageRequest);
    void updatePlanCode(@Param("planManageRequest") PlanManageRequest planManageRequest);

    List<PlanManageRequest> planCheck(@Param("planManageRequest") PlanManageRequest planManageRequest);
}
