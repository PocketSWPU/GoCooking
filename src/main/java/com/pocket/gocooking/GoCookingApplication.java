package com.pocket.gocooking;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@MapperScan("com.pocket.gocooking.system.mapper")
@Transactional(rollbackFor = Exception.class)
@EnableCaching
public class GoCookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoCookingApplication.class, args);
    }

    @Bean
    public Interceptor[] plugins() {
        return new Interceptor[]{new PageInterceptor()};
    }

}
