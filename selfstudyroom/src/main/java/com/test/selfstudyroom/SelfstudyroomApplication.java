package com.test.selfstudyroom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.test.selfstudyroom.mapper")
@SpringBootApplication
public class SelfstudyroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfstudyroomApplication.class, args);
    }

}
