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
public class ParameterNumerRequest implements Serializable {


    private Long parNumerId;

    private Long parId;

    private String parNumer;

    private String serialOrder;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
