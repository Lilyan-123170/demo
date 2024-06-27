package com.example.demo.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
public class ParameterRequest implements Serializable {


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
