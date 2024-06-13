package com.example.demo.service;

import com.example.demo.bo.ParameterBO;
import com.example.demo.entity.Column;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author WuYunhan
 * @since 2024-05-26
 */
public interface IColumnService extends IService<Column> {
    List<Double> evaluate(Long couId, List<ParameterBO> parameterBOList, int n);

    Column getColumn(Long columnId);
}
