package com.coocap.uni.service.user.service.api;

import com.coocap.uni.service.user.service.hystrix.OrderRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "order-service", fallback = OrderRemoteHystrix.class)
public interface OrderRemoteService {

    @GetMapping("/order/getOrderPort")
    String getOrderPort();

}
