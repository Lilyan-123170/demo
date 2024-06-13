package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公式id，主键自增
     */
    @TableId(value = "COL_ID", type = IdType.AUTO)
    private Long colId;

    private String colName;

    private String description;

    private String type;

    private String status;

    /**
     * 参数序列
     */
    private String paraSeq;

    private String calSeq;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
