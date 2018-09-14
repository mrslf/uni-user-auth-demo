package com.coocap.uni.service.user.controller;


import com.coocap.uni.service.user.service.api.OrderRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OrderRemoteService orderRemoteService;

    @Value("${server.port}")
    String port;

    /**
     * 获取用户服务的端口
     * @return
     */
    @GetMapping("/getUserPort")
    public String getUserPort() {
        return "用户服务端口:" + port;
    }

    /**
     * 获取订单服务的端口
     * @return
     */
    @GetMapping("/getOrderPort")
    public String getOrderPort() {
        return "用户服务调用订单服务获取端口:" + orderRemoteService.getOrderPort();
    }
}