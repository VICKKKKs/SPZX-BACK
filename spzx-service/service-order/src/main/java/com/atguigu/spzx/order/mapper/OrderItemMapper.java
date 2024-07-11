package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderItem;

import java.util.List;

public interface OrderItemMapper {
    void insertOrderItem(OrderItem orderItem);

    List<OrderItem>  selectByOrderId(Long orderId);
}
