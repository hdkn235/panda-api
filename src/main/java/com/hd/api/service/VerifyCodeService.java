package com.hd.api.service;

import com.hd.api.constant.VerifyCodeEnum;

public interface VerifyCodeService {

    /**
     * 发送验证码
     *
     * @param phone
     * @param type
     * @return
     */
    void send(String phone, VerifyCodeEnum type);

    /**
     * 检查验证码
     *
     * @param phone
     * @param type
     * @param code
     * @return
     */
    boolean verify(String phone, VerifyCodeEnum type, String code);

    /**
     * 使验证码失效
     *
     * @param phone
     * @param type
     * @return
     */
    void expire(String phone, VerifyCodeEnum type);

}
