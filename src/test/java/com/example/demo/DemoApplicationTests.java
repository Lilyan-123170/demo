package com.example.demo;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        // 自定义函数：计算下一个数列值
        Function nextValueFunction = new Function("next", 2) {
            @Override
            public double apply(double... args) {
                double xn = args[0]; // 当前数列值
                double y = args[1];  // 参数 y
                return Math.sin(xn) + Math.pow(y, 6); // 计算下一个数列值
            }
        };

        // 设置初始值
        double initialX = 1.0;
        double y = 2.0;

        // 构建表达式
        String expressionString = "next(p11, p12)";
        String s = "p11,p12";
        String[] tokens = s.split(",");
        Set<String> variables = new HashSet<>(Arrays.asList(tokens));
        Expression expression = new ExpressionBuilder(expressionString)
                .variables(variables)
                .function(nextValueFunction)
                .build()
                .setVariable("p11", initialX)
                .setVariable("p12",y);

        // 计算下一个数列值
        double nextValue = expression.evaluate();

        // 输出结果
        System.out.println("Next value: " + nextValue);


    }

}
