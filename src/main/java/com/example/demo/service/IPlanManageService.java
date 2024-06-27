package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.bo.ParameterBO;
import com.example.demo.entity.Column;
import com.example.demo.entity.PlanManage;
import com.example.demo.request.PlanManageRequest;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liyimei
 * @since 2024-05-26
 */
public interface IPlanManageService extends IService<PlanManage> {

    void planHistRecord(Map<String,Object> map);
    void planCreate(PlanManageRequest planManageRequest);

    Map planEdit(Map<String,Object> map);
    Map planCopy(Map<String,Object> map);

    void planDelete(Map<String,Object> map);
    List planCheck(PlanManageRequest planManageRequest);

}
