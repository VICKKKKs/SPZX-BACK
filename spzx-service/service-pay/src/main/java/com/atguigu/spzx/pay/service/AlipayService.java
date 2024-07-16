package com.atguigu.spzx.pay.service;

import java.util.Map;

public interface AlipayService {
    String submitAlipay(String orderNo);

    void notifyPay(Map<String, String> paramMap);
}
