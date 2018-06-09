package com.hd.api.thirdly.impl;

import com.hd.api.constant.VerifyCodeEnum;
import com.hd.api.thirdly.SMSManager;
import com.hd.api.utils.http.HttpRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSManagerImpl implements SMSManager {

    @Value("#{api.sms_appid}")
    private String APPID;

    @Value("#{api.sms_appkey}")
    private String APPKEY;

    @Value("#{api.sms_url}")
    private String URL;

    @Value("#{api.sms_invite}")
    private String SMS_INVITE;

    @Value("#{api.sms_signup}")
    private String SMS_SIGNUP;

    @Value("#{api.sms_refind}")
    private String SMS_REFIND;

    @Override
    public Boolean send(String param) {
        String jsonResult = HttpRequestUtil.sendPost(URL, param);
        if (StringUtils.isNotEmpty(jsonResult)) {
            JSONObject resultObj = new JSONObject(jsonResult);
            return resultObj.getString("status").equals("success");
        }
        return false;
    }

    @Override
    public Boolean sendVerifyCode(String code, String phone, VerifyCodeEnum type) {
        JSONObject varsObj = new JSONObject();
        varsObj.put("code", code);
        // 短信模板ID
        String projectId = null;
        if (type.equals(VerifyCodeEnum.SIGN_UP)) {
            projectId = SMS_SIGNUP;
        } else if (type.equals(VerifyCodeEnum.REFIND)) {
            projectId = SMS_REFIND;
        } else {
            return false;
        }
        String param = String.format("appid=%s&signature=%s&project=%s&to=%s&vars=%s", APPID, APPKEY, projectId, phone,
                varsObj.toString());
        return send(param);
    }

    @Override
    public Boolean sendInviteCode(String code, String orgName, String phone) {
        JSONObject varsObj = new JSONObject();
        varsObj.put("code", code);
        varsObj.put("orgname", orgName);
        String param = String.format("appid=%s&signature=%s&project=%s&to=%s&vars=%s", APPID, APPKEY, SMS_INVITE, phone,
                varsObj.toString());
        return send(param);
    }
}
