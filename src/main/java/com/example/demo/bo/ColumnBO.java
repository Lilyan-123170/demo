package com.example.demo.bo;

import lombok.*;

@Getter
@Setter
@ToString
public class ColumnBO {

    private Long colId;

    private String colName;


    private String type;

    private String paraSeq;

    private String calSeq;
}
