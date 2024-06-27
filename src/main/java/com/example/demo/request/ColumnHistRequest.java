package com.example.demo.request;

import lombok.Data;

import java.util.Date;

@Data
public class ColumnHistRequest {
//    公式ID
    private Long colId;
//    公式名称
    private String colName;
//    公式描述
    private String description;
//    分段函数规则
    private String colRule;
//    离散或连续
    private String type;
//    公式状态
    private String status;
//    计算序列
    private String calSeq;

    private String calSeqContent;

    private String planCode;
    private Long planId;

    //创建时间
    private Date creatTime;
    //修改时间
    private Date updateTime;

}
