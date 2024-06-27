package com.example.demo.request;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Liyimei
 * @since 2024-05-26
 */
@Data
public class ParameterHistRequest implements Serializable {


    private Long parId;

    private String parName;

    private Object parVal;

    private String type;

    private Long colId;

    private Long callColId;

    private String planCode;
    private Long planId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
