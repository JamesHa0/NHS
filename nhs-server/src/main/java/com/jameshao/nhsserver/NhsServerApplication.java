package com.jameshao.nhsserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jameshao.nhsserver.mapper")
public class NhsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NhsServerApplication.class, args);
    }

}
