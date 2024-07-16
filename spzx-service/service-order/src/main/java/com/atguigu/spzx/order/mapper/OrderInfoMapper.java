package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    void insertOrder(OrderInfo orderInfo);

    OrderInfo selectById(Long orderId);

    List<OrderInfo> selectOrderPage(Long userId, Integer orderStatus);

    OrderInfo selectByOrderNo(String orderNo);
}
