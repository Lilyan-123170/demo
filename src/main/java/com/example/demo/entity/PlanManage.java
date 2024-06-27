package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@Getter
@Setter
@ToString
public class PlanManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公式id，主键自增
     */
    @TableId(value = "PLAN_ID", type = IdType.AUTO)
    private Long planId;

    private String planCode;

    private String planName;


    private String planDescription;

    private String planState;

    private String createUserCode;
    private String createUserName;
    private String updateUserCode;
    private String updateUserName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
