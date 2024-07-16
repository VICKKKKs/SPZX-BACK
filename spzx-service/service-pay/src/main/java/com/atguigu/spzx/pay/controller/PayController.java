package com.atguigu.spzx.pay.controller;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.pay.service.AlipayService;
import com.atguigu.spzx.pay.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order/alipay/")
public class PayController {

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("submitAlipay/{orderNo}")
    public Result<String> submitAlipay(@PathVariable("orderNo") String orderNo) {
        String form = alipayService.submitAlipay(orderNo);
        return Result.ok(form);
    }

    @PostMapping("notify")
    public String notify(@RequestParam Map<String,String> paramMap, HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        alipayService.notifyPay(paramMap);
        System.out.println("支付宝回调接口");
        return "success";
    }

    @GetMapping("test")
    public String test() {
        return "success";
    }
}
