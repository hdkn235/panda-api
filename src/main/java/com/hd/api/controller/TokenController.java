package com.hd.api.controller;

import com.hd.app.param.LoginParam;
import com.hd.api.dto.LoginDTO;
import com.hd.api.service.LoginUserService;
import com.hd.app.security.IgnoreSecurity;
import com.hd.app.bean.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 令牌控制器
 */
@RestController
@RequestMapping("/tokens")
public class TokenController extends BaseController {

    @Autowired
    private LoginUserService luService;

    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    @IgnoreSecurity
    @RequestMapping(method = RequestMethod.POST)
    public Response login(@Valid LoginParam param) {
        LoginDTO dto = luService.login(param.getPhone(), param.getPassword(), param.getClientId());
        return new Response().success(dto);
    }

    /**
     * 用户退出
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public Response logout() {
        luService.logout(getToken());
        return new Response().success();
    }

}
