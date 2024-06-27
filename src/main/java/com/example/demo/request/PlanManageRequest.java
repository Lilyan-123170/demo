package com.example.demo.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanManageRequest {
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
