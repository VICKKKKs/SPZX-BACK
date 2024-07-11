package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;

public interface OrderInfoMapper {
    void insertOrder(OrderInfo orderInfo);

    OrderInfo selectById(Long orderId);
}
