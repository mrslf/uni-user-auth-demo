package com.coocap.uni.service.user.service.hystrix;

import com.coocap.uni.service.user.service.api.OrderRemoteService;
import org.springframework.stereotype.Component;

@Component
public class OrderRemoteHystrix implements OrderRemoteService {
    @Override
    public String getOrderPort() {
        return "熔断:订单服务调用失败";
    }
}
