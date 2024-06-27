package com.example.demo.controller;


import com.example.demo.request.ColumnRequest;
import com.example.demo.request.ParameterRequest;
import com.example.demo.request.PlanManageRequest;
import com.example.demo.service.IColumnService;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;
import java.util.ArrayList;
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
@RequestMapping("/demo/column")
public class ColumnController {
    @Resource
    private IColumnService columnService;


    @RequestMapping(value="/columnInsert")              //    @Authorize           确保只有经过授权的用户才能访问这些方法或类
    public Map columnInsertData(@RequestBody Map<String,Object> map) {
        Map<String,Object> resultMap=columnService.insertColumn(map);
        return resultMap;
    }
    @RequestMapping(value="/columnUpdate")              //    @Authorize           确保只有经过授权的用户才能访问这些方法或类
    public Map columnUpdateData(@RequestBody Map<String,Object> map) {
        Map resultMap=columnService.updateColumn(map);return resultMap;
    }
    @RequestMapping(value="/columnDelete")              //    @Authorize           确保只有经过授权的用户才能访问这些方法或类
    public void columnDeleteData(@RequestBody Map<String,Object> map) {
        columnService.deleteColumn(map);
    }
    @RequestMapping(value="/selectColumn")              //    @Authorize           确保只有经过授权的用户才能访问这些方法或类
    public Map selectColumn(@RequestBody PlanManageRequest planManageRequest) {

        Map<String,Object> resultMap=columnService.selectColumn(planManageRequest);

        return resultMap;
    }

}

