package com.coocap.uni.service.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Value("${server.port}")
    private String port;

    /**
     * 获取服务端口号
     * @return
     */
    @GetMapping("/getOrderPort")
    public String getOrderPort() {
        return "订单服务端口:" + port;
    }

}
