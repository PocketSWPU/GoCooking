package com.pocket.gocooking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pocket.gocooking.system.mapper")
public class GoCookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoCookingApplication.class, args);
    }
}
