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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@MapperScan("com.example.demo.mapper")
@SpringBootTest
public class ColumnTest {
    @Autowired
    private ColumnServiceImpl columnService;

    @Test
    public void selectAll() {
        String s =  columnService.getColumn(3013L).getCalSeq();
        System.out.println(s);

    }
    @Test
    public void calculateTest(){
        EntityToBoConverter entityToBoConverter = new EntityToBoConverter();

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sdf.format(d);
        System.out.println("开始时间");
        System.out.println(sj);

        Column column  = columnService.getColumn(3013L);
        ColumnBO columnBO = EntityToBoConverter.convert(column,ColumnBO.class);
//        参保率*人口数
        ParameterBO para1 = new ParameterBO();
        para1.setParaId(101L);
        para1.setValue("88,87,32,12,34,23,76,54,89,11,8,6,13,5,14,87,23,90,33,76,4,56,48,33,10,18,65,39,72,93,28,49,64,85,21,6,4,19,7,90,98,77,23,56,76,1,67,3,8,76,34,90,22,7,3,45,78,76,55,44,37,98,56,33,90,45,1,34,87,55,29,98,33,12,16,19,28,98,56,59,37");
//        城镇化率基准值
        ParameterBO para2 = new ParameterBO();
        para2.setParaId(1L);
        para2.setValue("0.7");
//        年份
        ParameterBO para3 = new ParameterBO();
        para3.setParaId(2L);
        para3.setValue("0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80");
//        就业率
        ParameterBO para4 = new ParameterBO();
        para4.setParaId(4L);
        para4.setValue("0.95");
//        制度覆盖率基准值
        ParameterBO para5 = new ParameterBO();
        para5.setParaId(5L);
        para5.setValue("0.7");
//        城镇化率步长
        ParameterBO para6 = new ParameterBO();
        para6.setParaId(6L);
        para6.setValue("0.06");
//        制度覆盖率步长
        ParameterBO para7 = new ParameterBO();
        para7.setParaId(7L);
        para7.setValue("0.1");
//        社会平均工资
        ParameterBO para8 = new ParameterBO();
        para8.setParaId(8L);
        para8.setValue("78052,78087,79132,79212,80034,80123,80176,90054,90089,90110,100008,100026,100033,100057,101414,110087,110223,110490,125033,126076,130504,130556,130948,131233,131210,131288,135265,133954,137211,139300,132800,134976,136465,138587,140021,140645,140944,143219,142267,147890,149098,150077,155423,156556,157676,157911,158767,158893,159878,161176,162234,163443,164322,165837,166533,167145,168178,169876,170155,170244,171037,171298,172356,173433,174590,176545,177851,178734,179087,180055,181429,182598,183633,184812,185116,186219,187328,188898,189056,196559,198037");
        para8.setCallColId(100006L);
//        实际GDP增长率基准值
        ParameterBO para9 = new ParameterBO();
        para9.setParaId(9L);
        para9.setValue("0.6");
//        实际GDP增长率步长
        ParameterBO para10 = new ParameterBO();
        para10.setParaId(10L);
        para10.setValue("0.03");
        // 通胀率
        ParameterBO para11 = new ParameterBO();
        para11.setParaId(11L);
        para11.setValue("0.02");
        // 社会平均工资增长与实际GDP增长的差额
        ParameterBO para12 = new ParameterBO();
        para12.setParaId(12L);
        para12.setValue("0.01");
        // 实际缴费率
        ParameterBO para13 = new ParameterBO();
        para13.setParaId(13L);
        para13.setValue("0.65");
        // 人均待遇
        ParameterBO para14 = new ParameterBO();
        para14.setParaId(14L);
        para14.setValue("7503,7087,7132,7212,8034,8013,8076,9054,9089,9010,9008,9026,9033,6057,9414,8087,9223,8490,7033,8076,7504,5556,6948,8233,9210,8678,5265,7954,7211,9300,6800,4976,6465,8587,4021,4645,4094,4329,4227,7890,9098,5007,5423,6556,7676,7911,8767,8893,5987,6116,6234,6343,6422,6583,6633,6745,6878,6987,7015,7044,7107,7198,7236,7343,7890,6545,7851,8734,9087,8055,8142,8259,8363,8481,5116,6219,7328,8898,9056,6559,8037");
        // 社会平均工资增长与实际GDP增长的比值
        ParameterBO para15 = new ParameterBO();
        para15.setParaId(15L);
        para15.setValue("0.3");
        // 目标替代率
        ParameterBO para16 = new ParameterBO();
        para16.setParaId(16L);
        para16.setValue("0.05");
        // 国内生产总值
        ParameterBO para17 = new ParameterBO();
        para17.setParaId(17L);
        para17.setValue("68503,68087,70132,73212,76034,78123,80196,80554,84089,91110,100508,107026,108033,111057,118424,117087,113223,114490,120033,121076,123504,130526,130998,132233,134210,135288,135765,134954,139211,139300,139900,139976,139465,139587,140021,140645,147944,146219,142267,149890,149898,150177,153423,154586,155876,156911,157767,158893,159178,161076,162034,163243,164022,165237,166133,167045,167178,169376,170255,170249,171237,171218,172356,174433,174990,176045,177351,178134,179987,180055,180429,181598,182633,183812,184116,185219,186328,187634,199416,196009,197423");
        // 补贴比例
        ParameterBO para18 = new ParameterBO();
        para18.setParaId(18L);
        para18.setValue("0.01");

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
        parameterBOList.add(para11);
        parameterBOList.add(para12);
        parameterBOList.add(para13);
        parameterBOList.add(para14);
        parameterBOList.add(para15);
        parameterBOList.add(para16);
        parameterBOList.add(para17);
        parameterBOList.add(para18);

//        parameterBOList.add(para7);
//        parameterBOList.add(para8);
//        parameterBOList.add(para9);
//        parameterBOList.add(para10);
        String exp = columnBO.getCalSeq();
        try {
//            exp="p1001*p1002*c1+p1004-p1003";
//            Map<String,Double> varMap = columnService.variablesMap(exp,parameterBOList,0);
//            System.out.println(varMap);
            List<Double> res = columnService.evaluate(3013L,parameterBOList,80);
            System.out.println(res);
            Date d2 = new Date();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sj2 = sdf2.format(d2);
            System.out.println("结束时间");
            System.out.println(sj2);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void extractVariablesTest(){
//        Column column  = columnService.getColumn(100001L);
//        ParameterBO para1 = new ParameterBO();
//        para1.setParaId(1001L);
//        para1.setValue("901,903");
//        ParameterBO para2 = new ParameterBO();
//        para2.setParaId(1002L);
//        para2.setValue("902");
//        List<ParameterBO> parameterBOList = new ArrayList<>();
//        parameterBOList.add(para1);
//        parameterBOList.add(para2);
//
//        Map<String,Double> map = columnService.variablesMap(column.getCalSeq(),parameterBOList,1);
//        System.out.println(map);
//    }
}
