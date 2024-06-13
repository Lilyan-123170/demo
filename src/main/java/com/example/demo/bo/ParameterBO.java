package com.example.demo.bo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ParameterBO {
    private Long paraId;

    private String paraName;

    private String type;

    private String value;

    private Long colId;

    private Long callColId;

}
