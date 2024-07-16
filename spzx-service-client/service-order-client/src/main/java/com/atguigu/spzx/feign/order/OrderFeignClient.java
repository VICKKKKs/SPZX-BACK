package com.atguigu.spzx.feign.order;

import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-order")
public interface OrderFeignClient {

    @GetMapping("/api/order/orderInfo/auth/getByOrderNo/{orderNo}")
    public Result<OrderInfo> getByOrderNo(@PathVariable String orderNo);
}
