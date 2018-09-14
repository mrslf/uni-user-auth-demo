package com.coocap.uni.service.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户微服务启动类
 * @author sunlinfeng
 * @date  
 */
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class UniUserServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(UniUserServiceApplication.class);

    /**
     * 启动方法入口
     * @param args
     */
    public static void main(String[] args) {

        logger.info("**********启动订单服务**********");
        SpringApplication.run(UniUserServiceApplication.class, args);
        logger.info("**********启动用户服务成功**********");
    }
}
