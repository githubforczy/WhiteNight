package com.whitenight.gate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whitenight.gate.model")
public class GateApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateApplication.class, args);
    }

}
