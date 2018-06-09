package com.hd.api.service.impl;

import com.hd.api.constant.CodeEnum;
import com.hd.api.constant.ErrorInfoConst;
import com.hd.api.constant.VerifyCodeEnum;
import com.hd.api.dao.LoginUserMapper;
import com.hd.api.exception.ServiceException;
import com.hd.api.manager.VerifyCodeManager;
import com.hd.api.model.LoginUser;
import com.hd.api.service.VerifyCodeService;
import com.hd.api.thirdly.SMSManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private VerifyCodeManager vcManager;

    @Autowired
    private LoginUserMapper luMapper;

    @Autowired
    private SMSManager smsManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void send(String phone, VerifyCodeEnum type) {
        // 找回密码需要验证用户是否存在
        LoginUser lu = luMapper.selectByPhone(phone);

        if (lu == null && type.equals(VerifyCodeEnum.REFIND)) {
            throw new ServiceException(CodeEnum.USER_NULL, ErrorInfoConst.USER_NOT_EXIST);
        } else if (lu != null && type.equals(VerifyCodeEnum.SIGN_UP)) {
            throw new ServiceException(ErrorInfoConst.USER_EXIST);
        }

        // 生成短信验证码
        String vcode = vcManager.create(phone, type);

        // 发送短信验证码
        Boolean result = smsManager.sendVerifyCode(vcode, phone, type);

        if (!result) {
            throw new ServiceException(ErrorInfoConst.VC_SEND_ERROR);
        }
    }

    @Override
    public boolean verify(String phone, VerifyCodeEnum type, String code) {
        return vcManager.verify(phone, type, code);
    }

    @Override
    public void expire(String phone, VerifyCodeEnum type) {
        vcManager.delete(phone, type);
    }

}
