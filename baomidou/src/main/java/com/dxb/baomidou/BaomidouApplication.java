package com.dxb.baomidou;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.dxb.baomidou.mapper")
public class BaomidouApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaomidouApplication.class, args);
    }

}
