package com.atguigu.spzx.order.service;

import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.order.TradeVo;
import com.github.pagehelper.PageInfo;

public interface OrderInfoService {
    TradeVo trade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> list(Integer page, Integer limit, Integer orderStatus);
}
