package com.example.demo;

import com.example.demo.bo.ColumnBO;
import com.example.demo.entity.Column;
import com.example.demo.service.impl.ColumnServiceImpl;
import com.example.demo.utils.EntityToBoConverter;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.example.demo.mapper")
@SpringBootTest
public class UitlsTests {
    @Autowired
    private ColumnServiceImpl columnServiceImpl;

    @Test
    public void test() {
        String s = "p1001*c1002*p1003";
        ColumnServiceImpl columnService = new ColumnServiceImpl();
        System.out.println(columnService.extractVariables(s));
    }

    @Test
    public void convertTest() {
        Column column = columnServiceImpl.getColumn(100001L);
        ColumnBO columnBO = EntityToBoConverter.convert(column, ColumnBO.class);
        System.out.println(column);
        System.out.println(columnBO);
        System.out.println(columnBO.toString());
    }
}
