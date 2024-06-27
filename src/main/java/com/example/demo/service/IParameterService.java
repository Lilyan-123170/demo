package com.example.demo.service;

import com.example.demo.entity.Parameter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuYunhan
 * @since 2024-05-26
 */
public interface IParameterService extends IService<Parameter> {
    void deleteParameter(Map<String,Object> map);

}
