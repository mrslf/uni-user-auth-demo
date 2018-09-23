package com.coocap.uni.sso.server;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 单点登录服务启动类
 * @author sunlinfeng
 * @date  
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.coocap.uni.sso.server.mapper","com.coocap.uni.sso.server.mapper.**"})
public class SsoServerApplication {

    /**
     * 启动类方法入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

}
