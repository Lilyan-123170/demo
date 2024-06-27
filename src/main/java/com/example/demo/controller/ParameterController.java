package com.example.demo.controller;


import com.example.demo.service.IColumnService;
import com.example.demo.service.IParameterService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@RequestMapping("/demo/parameter")
public class ParameterController {
    @Resource
    private IParameterService parameterService;

    @RequestMapping(value="/parameterDelete")
    @Transactional(rollbackFor = Exception.class)//    @Authorize           确保只有经过授权的用户才能访问这些方法或类
    public void parameterDeleteData(@RequestBody Map<String,Object> map) {
        parameterService.deleteParameter(map);
    }

}

