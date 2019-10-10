package com.newer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.newer.mapper")
public class RizhiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RizhiApplication.class, args);
    }

}
