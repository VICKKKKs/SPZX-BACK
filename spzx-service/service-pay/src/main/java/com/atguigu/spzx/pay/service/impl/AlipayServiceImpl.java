package com.atguigu.spzx.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.atguigu.spzx.feign.order.OrderFeignClient;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.entity.order.OrderItem;
import com.atguigu.spzx.model.entity.pay.PaymentInfo;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.entity.user.UserInfoAuthContextUtil;
import com.atguigu.spzx.pay.service.AlipayService;
import com.atguigu.spzx.pay.service.PaymentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlipayServiceImpl implements AlipayService {


    @Autowired
    OrderFeignClient orderFeignClient;

    @Autowired
    PaymentService paymentService;

    @SneakyThrows
    @Override
    public String submitAlipay(String orderNo) {
        // 公共参数
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDNPwJHgAQk6KscOtpAZ/1M4yJKxXMpgRyssEZWqTxL2ubjhCPKu+/FilLKzKDulZFFZ7PyMI4p3CYG4rOqabQ+vIXq310sC2QRCmcMJQNUesM4ml76t/2IT/401cyMJmCn+E+gR33IMjxCKmxew2iax9B4xi9SP0EJwnPaVAJRkwlw1jrVvmu3WrnEO9LKzwAKdXaMP7KhQ2kUfk51yQyGpXDaEeNk5KNVdp0MNQZSPmrRh/a9KAw7qJiyU+WW1RlBxrgurELOIrLhv2QvU32Q4qie0CA5zbL2P+NI3wZxe8bys7e+CJj7yzPhsy7KKRlIFJWJ1scrDMtLqmK6GVLJAgMBAAECggEAM2BjXKyB3Q9UzcM5oMenE1kD232jfy8vk1aotMa2LjLEew03vGbg9SzT3qnsWuybqaj01VosK/RMnIqg+LZvBQlB6HIzK3RKfy2Ab/zP4ZS9S2zw+4eU8ks70dP22No+da1lmTRsGvHgPZvK9smpZJ1B5QmPDOKCkEPZpjxDPFHEVmDoL+nYJkQKx3T9tyRToV4MQv0/iCy3JzfIeufFGUdUKen7ufSb4V0zrMEaWpantyDFHNn0PXwieIKTmJEl7h5v6Q1Yciny5N+FMy1RuUmzDX0boJRpz16ciQSNivYzczpwrI9G6de4dd7NNB3EseVbvUaLUMlNu12IJUZ+EQKBgQDnum0lMCLzj2pNIQrt8bs0/tvp6sB8B2EkuLVUiFaeP6pcQjhRxLoRfpnCw6YKDHpTrR0XlqwFGuYJnXa6hKE7SgCYaJnuroh6Q402wi5UsRhrgKuhRofCZTgj1Pn5znSucVh0Q1O2Dy1RhxRZFKSn+Y2HNE0T7XE7UAYCY0myZQKBgQDivn10syLndvm5aAE7zAPpKvCNkQqSyfGCC895AP8EIZ2hfKXoBBBzERFs4KH5m9/M1arFOIHY2jA3Rxh/LgfCc7v6/C+Miw1imSIuYgq58cKmPuUbSTmTEJQ1vpAyPKG4H0PjijAAomO0O2QItRywG0Op5/SF0zABM9DjsHumlQKBgF6Ug8ni7NEWXx/d9bUKrgtCXoYKD8hPxsGTNkerYhczCxie2pfdQMi1MJMDAfkmAKO4i6A+GjX3NZ8FCxmz2o71dGrAftuhlwi4G6IW/YwJwFrYaLTpsa2RXKmbWvAv6QXuM1k4xakQZsHY7WAygcYhoDdoDHGuv0GjNuCwn7rVAoGBAMc30Ohw3/D4cq7492R/eswpoxLu5ZhU//FfyRkqy+ToWCPjba0trPo8e8/qicXSs064SINhg4xxH73hpF9RpRWvM/FwE6LrJYaEvyh7kasQBBKm3gscSg6xmVajKPRKm1g3eCF6b3SPOQTIzbpszljYi5nDp+7Qv7O+wADdNbcFAoGBAKuh/axhChD0xFCtD0D9VmNB9xBeIHOU0IJTg+HEJTHBKF6+3ZcxOh4w+o6UnxwjRxmVWev0yoLiJVwVqDxmG/JhWRrsXVOm7hW4GErrPrQUbni/rf8b6ZHcqJI4Ct0TrzSOxJTW8k4t8EZr+hH7ssWbgJUaHB4tEBKM8hUw3a23";
        String publickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmw8psf//i/tT0RKFYes3rskX5jysYDwIGJLINmu8pJVPHkFC0n5LXOWZPeoyjFMzRHTRFVJKXdd0s43OTVDSuMhxcUy3dplRgYLWYJHFEqIXFS9gG59o/mEeC2wBq0yvSdyzYtcFHWmFvn9r+QWXXHc29zZMUaTBw5NPwDFuHOyMF1dW2+ziHK+CQOatipUKj0E8iot3BA4Z/pE3lO5Q7xktajJkS4aae6mwmt0GjPJuuFSgRqGJ/v10EegmtZMxWBrBerfRVOf2cUNCZewoWFMUWJK+64QLStLp9CLIp3v+e68SGQCfSWSFJIPS6QYpSx3Efn3EyScsTJ1yldu//wIDAQAB";
        // 使用阿里的api，生成跳转表单,公共参数
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi-sandbox.dl.alipaydev.com/gateway.do", "9021000139615086", privateKey, "json", "utf-8", publickey, "RSA2");
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

//        // 同步url
        request.setReturnUrl("http://192.168.222.20/#/pages/money/paySuccess");
//        // 异步url
        request.setNotifyUrl("http://vicapp.natapp1.cc/api/order/alipay/notify");
//
        // 请求参数
        JSONObject bizContent = new JSONObject();
        OrderInfo orderInfo = orderFeignClient.getByOrderNo(orderNo).getData();
        bizContent.put("out_trade_no", orderNo);
        bizContent.put("total_amount", orderInfo.getTotalAmount());
        List<OrderItem> orderItemList = orderInfo.getOrderItemList();
        String SkuNames = orderItemList.stream().map(OrderItem::getSkuName).collect(Collectors.joining(","));
        bizContent.put("subject", SkuNames);
        bizContent.put("product_code", "QUICK_WAP_WAY");
        String jsonStr = bizContent.toString();


        //        HashMap<String, Object> map = new HashMap<>();
//        map.put("out_trade_no","20210817010101004");
//        map.put("product_code","QUICK_WAP_WAY");
//        map.put("total_amount",0.01);
//        map.put("subject","测试商品");
//        String jsonMapStr = JSON.toJSONString(map);

//         将参数放入request，请求参数
        request.setBizContent(jsonStr);

        AlipayTradeWapPayResponse alipayTradeWapPayResponse = alipayClient.pageExecute(request);
        String form = alipayTradeWapPayResponse.getBody();
        System.out.println(form);

        System.out.println("在返回支付表单之前，插入支付数据，状态为未支付。。。payment_info");

        UserInfo userInfo = UserInfoAuthContextUtil.get();
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPayType(2);
        paymentInfo.setUserId(userInfo.getId());
        paymentInfo.setOrderNo(orderNo);
        paymentInfo.setOutTradeNo(orderNo);
        paymentInfo.setAmount(orderInfo.getTotalAmount());
        paymentInfo.setContent(SkuNames);
        paymentInfo.setPaymentStatus(0);
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setUpdateTime(new Date());
        paymentInfo.setIsDeleted(0);
        paymentService.insertPayment(paymentInfo);
        return form;
    }

    @SneakyThrows
    @Override
    public void notifyPay(Map<String, String> paramMap) {
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDNPwJHgAQk6KscOtpAZ/1M4yJKxXMpgRyssEZWqTxL2ubjhCPKu+/FilLKzKDulZFFZ7PyMI4p3CYG4rOqabQ+vIXq310sC2QRCmcMJQNUesM4ml76t/2IT/401cyMJmCn+E+gR33IMjxCKmxew2iax9B4xi9SP0EJwnPaVAJRkwlw1jrVvmu3WrnEO9LKzwAKdXaMP7KhQ2kUfk51yQyGpXDaEeNk5KNVdp0MNQZSPmrRh/a9KAw7qJiyU+WW1RlBxrgurELOIrLhv2QvU32Q4qie0CA5zbL2P+NI3wZxe8bys7e+CJj7yzPhsy7KKRlIFJWJ1scrDMtLqmK6GVLJAgMBAAECggEAM2BjXKyB3Q9UzcM5oMenE1kD232jfy8vk1aotMa2LjLEew03vGbg9SzT3qnsWuybqaj01VosK/RMnIqg+LZvBQlB6HIzK3RKfy2Ab/zP4ZS9S2zw+4eU8ks70dP22No+da1lmTRsGvHgPZvK9smpZJ1B5QmPDOKCkEPZpjxDPFHEVmDoL+nYJkQKx3T9tyRToV4MQv0/iCy3JzfIeufFGUdUKen7ufSb4V0zrMEaWpantyDFHNn0PXwieIKTmJEl7h5v6Q1Yciny5N+FMy1RuUmzDX0boJRpz16ciQSNivYzczpwrI9G6de4dd7NNB3EseVbvUaLUMlNu12IJUZ+EQKBgQDnum0lMCLzj2pNIQrt8bs0/tvp6sB8B2EkuLVUiFaeP6pcQjhRxLoRfpnCw6YKDHpTrR0XlqwFGuYJnXa6hKE7SgCYaJnuroh6Q402wi5UsRhrgKuhRofCZTgj1Pn5znSucVh0Q1O2Dy1RhxRZFKSn+Y2HNE0T7XE7UAYCY0myZQKBgQDivn10syLndvm5aAE7zAPpKvCNkQqSyfGCC895AP8EIZ2hfKXoBBBzERFs4KH5m9/M1arFOIHY2jA3Rxh/LgfCc7v6/C+Miw1imSIuYgq58cKmPuUbSTmTEJQ1vpAyPKG4H0PjijAAomO0O2QItRywG0Op5/SF0zABM9DjsHumlQKBgF6Ug8ni7NEWXx/d9bUKrgtCXoYKD8hPxsGTNkerYhczCxie2pfdQMi1MJMDAfkmAKO4i6A+GjX3NZ8FCxmz2o71dGrAftuhlwi4G6IW/YwJwFrYaLTpsa2RXKmbWvAv6QXuM1k4xakQZsHY7WAygcYhoDdoDHGuv0GjNuCwn7rVAoGBAMc30Ohw3/D4cq7492R/eswpoxLu5ZhU//FfyRkqy+ToWCPjba0trPo8e8/qicXSs064SINhg4xxH73hpF9RpRWvM/FwE6LrJYaEvyh7kasQBBKm3gscSg6xmVajKPRKm1g3eCF6b3SPOQTIzbpszljYi5nDp+7Qv7O+wADdNbcFAoGBAKuh/axhChD0xFCtD0D9VmNB9xBeIHOU0IJTg+HEJTHBKF6+3ZcxOh4w+o6UnxwjRxmVWev0yoLiJVwVqDxmG/JhWRrsXVOm7hW4GErrPrQUbni/rf8b6ZHcqJI4Ct0TrzSOxJTW8k4t8EZr+hH7ssWbgJUaHB4tEBKM8hUw3a23";
        String publickey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmw8psf//i/tT0RKFYes3rskX5jysYDwIGJLINmu8pJVPHkFC0n5LXOWZPeoyjFMzRHTRFVJKXdd0s43OTVDSuMhxcUy3dplRgYLWYJHFEqIXFS9gG59o/mEeC2wBq0yvSdyzYtcFHWmFvn9r+QWXXHc29zZMUaTBw5NPwDFuHOyMF1dW2+ziHK+CQOatipUKj0E8iot3BA4Z/pE3lO5Q7xktajJkS4aae6mwmt0GjPJuuFSgRqGJ/v10EegmtZMxWBrBerfRVOf2cUNCZewoWFMUWJK+64QLStLp9CLIp3v+e68SGQCfSWSFJIPS6QYpSx3Efn3EyScsTJ1yldu//wIDAQAB";
        // 使用阿里公钥验证请求的身份
        boolean signVerified = AlipaySignature.rsaCheckV1(paramMap, publickey, "utf-8", "RSA2");
        System.out.println("验签结果："+signVerified);
        System.out.println("支付宝回调接口");

        // 修改支付状态
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentStatus(1);
        paymentService.updatePayment(paymentInfo);
    }
}
