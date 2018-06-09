package com.hd.api.thirdly;

import com.hd.api.constant.VerifyCodeEnum;

public interface SMSManager {

	/**
	 * 发送短信
	 * 
	 * @param param
	 * @return
	 */
	Boolean send(String param);

	/**
	 * 发送验证码
	 * 
	 * @param code
	 * @param phone
	 * @param type
	 * @return
	 */
	Boolean sendVerifyCode(String code, String phone, VerifyCodeEnum type);

	/**
	 * 发送邀请码
	 * 
	 * @param code
	 * @param orgName
	 * @param phone
	 * @return
	 */
	Boolean sendInviteCode(String code, String orgName, String phone);
}
