package com.hd.api.controller;

import com.hd.api.constant.VerifyCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hd.api.service.VerifyCodeService;
import com.hd.app.security.IgnoreSecurity;
import com.hd.app.bean.Response;

/**
 * 验证码控制器
 */
@RestController
@RequestMapping("/verify_code")
public class VerifyCodeController extends BaseController {

    @Autowired
    private VerifyCodeService vcService;

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @param type  验证码类型：0 注册用户； 1 找回密码
     * @return
     */
    @IgnoreSecurity
    @RequestMapping(method = RequestMethod.POST)
    public Response sendVerifyCode(
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "type") Integer type) {
        vcService.send(phone, VerifyCodeEnum.values()[type]);
        return new Response().success();
    }

}
