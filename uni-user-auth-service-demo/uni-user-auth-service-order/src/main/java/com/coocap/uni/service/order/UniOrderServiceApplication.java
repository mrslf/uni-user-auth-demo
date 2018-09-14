package com.coocap.uni.service.order;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单微服务启动类
 * @author sunlinfeng
 * @date  
 */
@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class UniOrderServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(UniOrderServiceApplication.class);

    /**
     * 启动方法入口
     * @param args
     */
    public static void main(String[] args) {
        logger.info("**********启动订单服务**********");
        SpringApplication.run(UniOrderServiceApplication.class, args);
        logger.info("**********启动订单服务成功**********");
    }
}
