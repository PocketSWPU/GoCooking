package com.pocket.demo01;



import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/go_cooking";
        String username = "root";
        String password = "123456";
        String author = "PengZF";
        String outputDir = "C:\\Users\\Pocket\\IdeaProjects\\GoCooking\\src\\main\\java";
        String basePackage = "com.pocket.gocooking";
        String moduleName = "sys";
        String mapperLocation = "C:\\Users\\Pocket\\IdeaProjects\\GoCooking\\src\\main\\resources\\mapper\\" + moduleName;
        String[] tablesName = {"ingredient_table","dish_table"};
        String tablePrefix = "";
        String tableSuffix = "_table";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tablesName) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix) // 设置过滤表前缀
                            .addTableSuffix(tableSuffix);
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
