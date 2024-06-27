package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Getter
@Setter
public class ParameterNumer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PAR_NUMER_ID", type = IdType.AUTO)
    private Long parNumerId;

    private Long parId;

    private String parNumer;

    private String serialOrder;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
