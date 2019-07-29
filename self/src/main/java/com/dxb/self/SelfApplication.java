package com.dxb.self;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dxb.self.mapper")
public class SelfApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfApplication.class, args);
    }
}
