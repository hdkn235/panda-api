package com.hd.api.manager.impl;

import com.hd.api.constant.VerifyCodeEnum;
import com.hd.api.manager.VerifyCodeManager;
import com.hd.utils.common.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class VerifyCodeManagerImpl implements VerifyCodeManager {

    /**
     * 短信验证码过期时间
     */
    @Value("#{api.sms_expires_minute}")
    private String SMS_EXPIRES_MINUTE;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String create(String phone, VerifyCodeEnum type) {
        String vcode = CommonUtil.createVcode();
        Integer expire = Integer.parseInt(SMS_EXPIRES_MINUTE);
        String key = "vcode_" + phone + "_" + type.name();
        if (redisTemplate.hasKey(key)) {
            redisTemplate.boundValueOps(key).expire(expire, TimeUnit.MINUTES);
        } else {
            redisTemplate.boundValueOps(key).set(vcode, expire, TimeUnit.MINUTES);
        }
        return vcode;
    }

    @Override
    public Boolean verify(String phone, VerifyCodeEnum type, String vcode) {
        String key = "vcode_" + phone + "_" + type.name();
        String realVcode = redisTemplate.boundValueOps(key).get();
        return vcode.equals(realVcode);
    }

    @Override
    public String get(String phone, VerifyCodeEnum type) {
        String key = "vcode_" + phone + "_" + type.name();
        String realVcode = redisTemplate.boundValueOps(key).get();
        return realVcode;
    }

    @Override
    public void delete(String phone, VerifyCodeEnum type) {
        String key = "vcode_" + phone + "_" + type.name();
        redisTemplate.delete(key);
    }
}
