package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author WuYunhan
 * @since 2024-05-26
 */
@Getter
@Setter
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PARA_ID", type = IdType.AUTO)
    private Long paraId;

    private String paraName;

    private String type;

    private Long colId;

    private Long callColId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
