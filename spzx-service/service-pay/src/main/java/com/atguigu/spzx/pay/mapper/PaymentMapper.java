package com.atguigu.spzx.pay.mapper;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;

public interface PaymentMapper {
    void insert(PaymentInfo paymentInfo);

    void update(PaymentInfo paymentInfo);
}
