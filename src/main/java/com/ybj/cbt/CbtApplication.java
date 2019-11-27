package com.ybj.cbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ybj.cbt.mapper")
public class CbtApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbtApplication.class, args);
    }

}
