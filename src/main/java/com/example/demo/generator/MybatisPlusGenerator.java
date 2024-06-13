package com.example.demo.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.Collections;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        // 数据源配置
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(
                "jdbc:mysql://localhost:3306/calculate_engine?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf8",
                "test",
                "Wyh961130test#"
        );

        // 全局配置
        FastAutoGenerator.create(dataSourceConfigBuilder)
                .globalConfig(builder -> {
                    String projectPath = System.getProperty("user.dir");
                    builder.outputDir(projectPath + "/src/main/java") // 指定输出目录
                            .author("WuYunhan") // 设置作者
                            .disableOpenDir(); // 禁止打开输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("demo") // 设置模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("PARAMETER") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .enableLombok(); // 开启lombok
                    builder.controllerBuilder()
                            .enableRestStyle(); // 开启生成@RestController 控制器
                    builder.mapperBuilder()
                            .enableBaseResultMap() // 启用BaseResultMap
                            .enableBaseColumnList(); // 启用BaseColumnList
                })
                .execute();
    }
}
