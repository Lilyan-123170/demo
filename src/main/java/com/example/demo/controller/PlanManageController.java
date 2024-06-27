package com.example.demo.controller;


import com.example.demo.request.PlanManageRequest;
import com.example.demo.service.IColumnService;
import com.example.demo.service.IPlanManageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Liyimei
 * @since 2024-05-26
 */
@RestController
@ControllerAdvice
@RequestMapping("/demo/planManage")
public class PlanManageController {
    @Resource
    private IPlanManageService planManageService;

    @RequestMapping(value="/planHistRecord")
    @Transactional(rollbackFor = Exception.class)
    public void planHistRecord(@RequestBody Map<String,Object> map) {
        planManageService.planHistRecord(map);
    }

    @RequestMapping(value="/planCreate")
    @Transactional(rollbackFor = Exception.class)
    public PlanManageRequest planCreate(@RequestBody PlanManageRequest planManageRequest) {
        planManageService.planCreate(planManageRequest);
        return planManageRequest;
    }

    @RequestMapping(value="/planEdit")
    @Transactional(rollbackFor = Exception.class)
    public Map planEdit(@RequestBody Map<String,Object> map) {
        Map resultMap=planManageService.planEdit(map);
        return resultMap;
    }

    @RequestMapping(value="/planCopy")
    @Transactional(rollbackFor = Exception.class)
    public Map planCopy(@RequestBody Map<String,Object> map) {
        Map resultMap=planManageService.planCopy(map);
        return resultMap;
    }

    @RequestMapping(value="/planDelete")
    @Transactional(rollbackFor = Exception.class)
    public void planDelete(@RequestBody Map<String,Object> map) {
        planManageService.planDelete(map);
    }

    @RequestMapping(value="/planCheck")
    @Transactional(rollbackFor = Exception.class)
    public List planCheck(@RequestBody PlanManageRequest planManageRequest) {
        List resultList=planManageService.planCheck(planManageRequest);
        return resultList;
    }

}












