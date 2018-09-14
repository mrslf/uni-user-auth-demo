package com.coocap.uni.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * 注册中心启动类
 * @author sunlinfeng
 * @date  
 */
@SpringBootApplication
@EnableEurekaServer
public class UniEurekaApplication {

    private static final Logger logger = LoggerFactory.getLogger(UniEurekaApplication.class);

    /**
     * 启动方法入口
     * @param args
     */
    public static void main(String[] args) {
        logger.info("**********启动eureka注册中心服务**********");
        SpringApplication.run(UniEurekaApplication.class, args);
        logger.info("**********启动eureka注册中心服务成功**********");
    }


    
}
