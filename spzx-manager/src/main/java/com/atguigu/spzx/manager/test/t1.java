package com.atguigu.spzx.manager.test;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

public class t1 {
    public static void main(String[] args) {
        CircleCaptcha captcha = new CircleCaptcha(145,45,5,100);
        String code = captcha.getCode();
        String imageBase64 = captcha.getImageBase64Data();
        System.out.println(code);
        System.out.println("---------------------");
        System.out.println(imageBase64);
    }
}
