package com.hd.api.manager;

import com.hd.api.constant.VerifyCodeEnum;

public interface VerifyCodeManager {

    /**
     * 生成短信验证码
     *
     * @param phone
     * @param type
     * @return
     */
    String create(String phone, VerifyCodeEnum type);

    /**
     * 验证短信验证码
     *
     * @param phone
     * @param type
     * @param veryifyCode
     * @return
     */
    Boolean verify(String phone, VerifyCodeEnum type, String veryifyCode);

    /**
     * 获取短信验证码
     *
     * @param phone
     * @param type
     * @return
     */
    String get(String phone, VerifyCodeEnum type);

    /**
     * 删除短信验证码
     *
     * @param phone
     * @param type
     */
    void delete(String phone, VerifyCodeEnum type);
}
