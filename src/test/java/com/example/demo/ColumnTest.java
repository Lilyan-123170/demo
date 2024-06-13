package com.example.demo;

import com.example.demo.bo.ColumnBO;
import com.example.demo.bo.ParameterBO;
import com.example.demo.entity.Column;
import com.example.demo.service.impl.ColumnServiceImpl;
import com.example.demo.utils.EntityToBoConverter;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MapperScan("com.example.demo.mapper")
@SpringBootTest
public class ColumnTest {
    @Autowired
    private ColumnServiceImpl columnService;

    @Test
    public void selectAll() {
        String s =  columnService.getColumn(100001L).getCalSeq();
        System.out.println(s);

    }
    @Test
    public void calculateTest(){
        EntityToBoConverter entityToBoConverter = new EntityToBoConverter();
        Column column  = columnService.getColumn(100007L);
        ColumnBO columnBO = EntityToBoConverter.convert(column,ColumnBO.class);
        System.out.println(columnBO.toString());
        ParameterBO para1 = new ParameterBO();
        para1.setParaId(1001L);
        para1.setValue("11,1");
        ParameterBO para2 = new ParameterBO();
        para2.setParaId(1002L);
        para2.setValue("12,2");
        ParameterBO para3 = new ParameterBO();
        para3.setParaId(1003L);
        para3.setValue("3");
        ParameterBO para4 = new ParameterBO();
        para4.setParaId(1004L);
        para4.setValue("4");
        ParameterBO para5 = new ParameterBO();
        para5.setParaId(1005L);
        para5.setValue("5");
        ParameterBO para6 = new ParameterBO();
        para6.setParaId(1006L);
        para6.setValue("6");
        ParameterBO para7 = new ParameterBO();
        para7.setParaId(1007L);
        para7.setValue("7");
        ParameterBO para8 = new ParameterBO();
        para8.setParaId(1008L);
        para8.setValue("1");
        para8.setCallColId(100006L);
        ParameterBO para9 = new ParameterBO();
        para9.setParaId(1009L);
        para9.setValue("2");
        ParameterBO para10 = new ParameterBO();
        para10.setParaId(1010L);
        para10.setValue("3");
        List<ParameterBO> parameterBOList = new ArrayList<>();
        parameterBOList.add(para1);
        parameterBOList.add(para2);
        parameterBOList.add(para3);
        parameterBOList.add(para4);
        parameterBOList.add(para5);
        parameterBOList.add(para6);
        parameterBOList.add(para7);
        parameterBOList.add(para8);
        parameterBOList.add(para9);
        parameterBOList.add(para10);
        String exp = columnBO.getCalSeq();
        try {
            List<Double> res = columnService.evaluate(100007L,parameterBOList,80);
            System.out.println(res);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractVariablesTest(){
        Column column  = columnService.getColumn(100001L);
        ParameterBO para1 = new ParameterBO();
        para1.setParaId(1001L);
        para1.setValue("901,903");
        ParameterBO para2 = new ParameterBO();
        para2.setParaId(1002L);
        para2.setValue("902");
        List<ParameterBO> parameterBOList = new ArrayList<>();
        parameterBOList.add(para1);
        parameterBOList.add(para2);

        Map<String,Double> map = columnService.variablesMap(column.getCalSeq(),parameterBOList,1);
        System.out.println(map);
    }
}
