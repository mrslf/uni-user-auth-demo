package com.coocap.uni.oracle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.coocap.uni.oracle.mapper","com.coocap.uni.oracle.mapper.**"})
public class OracleServieApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleServieApplication.class, args);
    }


}
