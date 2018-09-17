package com.coocap.uni.sso.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 单点登录服务启动类
 * @author sunlinfeng
 * @date  
 */
@SpringBootApplication
public class SsoServerApplication {

    /**
     * 启动类方法入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

}
