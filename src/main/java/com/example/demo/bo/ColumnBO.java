package com.example.demo.bo;

import lombok.*;

@Getter
@Setter
@ToString
public class ColumnBO {

    private Long colId;

    private String colName;

    private String description;

    private String colRule;

    private String type;

    private String status;

    private String calSeq;


}
