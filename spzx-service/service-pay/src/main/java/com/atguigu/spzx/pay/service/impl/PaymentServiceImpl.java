package com.atguigu.spzx.pay.service.impl;



import com.atguigu.spzx.model.entity.pay.PaymentInfo;
import com.atguigu.spzx.pay.mapper.PaymentMapper;
import com.atguigu.spzx.pay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public void insertPayment(PaymentInfo paymentInfo) {
        paymentMapper.insert(paymentInfo);
    }

    @Override
    public void updatePayment(PaymentInfo paymentInfo) {
        paymentMapper.update(paymentInfo);
    }
}
