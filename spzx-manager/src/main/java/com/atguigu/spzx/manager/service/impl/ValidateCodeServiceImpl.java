package com.atguigu.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {

        // ValidateCodeVo: codeKey  codeValue(图片验证码)
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();

        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 20);

        // codeValue 是通过 circleCaptcha.getCode() 获取的验证码文本。
        String codeValue = circleCaptcha.getCode();
        // imageBase64 是通过 circleCaptcha.getImageBase64() 获取的验证码图片的 Base64 编码。
        String imageBase64 = circleCaptcha.getImageBase64();

        // 存入redis
        String codeKey = UUID.randomUUID().toString().replaceAll("-", "");

        redisTemplate.opsForValue().set("user:login:validatecode:" + codeKey, codeValue,5, TimeUnit.MINUTES);

        validateCodeVo.setCodeKey(codeKey);
        // data:image/png;base64,添加了这个，浏览器上才可以输入imageBase64看到直接的验证码图片
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);
        return validateCodeVo;
    }
}
