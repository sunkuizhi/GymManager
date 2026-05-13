package com.test.GymManager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.test.GymManager.mapper")
public class GymManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GymManagerApplication.class, args);
    }

}
