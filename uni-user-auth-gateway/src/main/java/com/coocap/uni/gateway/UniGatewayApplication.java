package com.coocap.uni.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 网关服务启动类
 * @author sunlinfeng
 * @date  
 */
@SpringBootApplication
public class UniGatewayApplication {

    /**
     * 启动方法入口
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(UniGatewayApplication.class, args);
    }

    /**
     * 配置route规则，java配置或者配置文件配置
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().route(
                r -> r.path("/qq/**")
                .and()
                .uri("http://www.qq.com"))
                .build();
    }

}
