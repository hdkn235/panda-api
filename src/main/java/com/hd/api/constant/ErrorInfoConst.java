package com.hd.api.constant;

/**
 * 错误信息提示
 * 
 * @author hoda
 *
 * @since 1.0
 */
public class ErrorInfoConst {

	/**
	 * 密码输入有误
	 */
	public final static String PASSWORD_WRONG = "用户名或密码错误";

	/**
	 * 用户不存在
	 */
	public final static String USER_NOT_EXIST = "用户不存在";

	/**
	 * 用户已存在
	 */
	public final static String USER_EXIST = "用户已存在";

	/**
	 * Token已过期
	 */
	public final static String TOKEN_EXPIRED = "Token已过期";

	/**
	 * 无效的Token
	 */
	public final static String TOKEN_INVALID = "无效的Token";

	/**
	 * 已超过每天最多发送短信验证码的次数
	 */
	public final static String VC_OVER_LIMIT = "已超过每天最多发送短信验证码的次数";

	/**
	 * 验证码发送失败
	 */
	public final static String VC_SEND_ERROR = "验证码发送失败";

	/**
	 * 邀请码发送失败
	 */
	public final static String IC_SEND_ERROR = "邀请码发送失败";

	/**
	 * 企业名称已经注册
	 */
	public final static String ORG_REGISTERED = "企业名称已经注册";

	/**
	 * 用户已经注册
	 */
	public final static String USER_REGISTERED = "用户已经注册";

	/**
	 * 手机号已经注册
	 */
	public final static String PHONE_REGISTERED = "手机号已经注册";

	/**
	 * 验证码输入有误
	 */
	public final static String VC_WRONG = "验证码输入有误";

	/**
	 * 验证码已过期，请重新获取
	 */
	public final static String VC_EXPIRED = "验证码已过期，请重新获取";

	/**
	 * 邀请码输入有误，请输入集团邀请码
	 */
	public final static String IC_WRONG_COMPANY = "邀请码输入有误，请输入集团邀请码";

	/**
	 * 邀请码输入有误，请输入集团或企业邀请码
	 */
	public final static String IC_WRONG_PERSON = "邀请码输入有误，请输入集团或企业邀请码";

	/**
	 * 未找到缓存
	 */
	public final static String CACHE_NO = "未找到缓存";


}
