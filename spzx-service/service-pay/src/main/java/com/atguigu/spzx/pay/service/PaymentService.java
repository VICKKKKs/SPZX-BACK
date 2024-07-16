package com.atguigu.spzx.pay.service;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;

public interface PaymentService {
    void insertPayment(PaymentInfo paymentInfo);

    void updatePayment(PaymentInfo paymentInfo);

}
