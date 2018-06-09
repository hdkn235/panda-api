package com.hd.api.service;

import com.hd.api.dto.LoginDTO;
import com.hd.api.model.LoginUser;

import java.util.List;

public interface LoginUserService extends BaseService<LoginUser> {

	/**
	 * 登录
	 * 
	 * @param phone
	 * @param pwd
	 * @param cid
	 * @return
	 */
	LoginDTO login(String phone, String pwd, String cid);

	/**
	 * 登录
	 * 
	 * @param phone
	 * @param pwd
	 * @param cid
	 * @return
	 */
	boolean login(String phone, String pwd);

	/**
	 * 企业用户注册
	 * 
	 * @param orgName
	 * @param userName
	 * @param phone
	 * @param verifyCode
	 * @param passWord
	 */
	void signUpCompany(String inviteCode, String orgName, String userName, String phone, String verifyCode,
                       String passWord);

	/**
	 * 个人用户注册
	 * 
	 * @param inviteCode
	 * @param userName
	 * @param phone
	 * @param verifyCode
	 * @param passWord
	 */
	void signUpPerson(String inviteCode, String userName, String phone, String verifyCode, String passWord);

	/**
	 * 忘记密码
	 * 
	 * @param phone
	 * @param pwd
	 * @param verifyCode
	 */
	boolean forgetPwd(String phone, String pwd, String verifyCode);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param pwd
	 * @param newPwd
	 */
	boolean modifyPwd(String userId, String pwd, String newPwd);

	/**
	 * 退出登录
	 * 
	 * @param token
	 * @return
	 */
	boolean logout(String token);

	/**
	 * 验证手机号
	 * 
	 * @param token
	 */
	void verifyPhone(String phone);
	
	/**
	 * 获取公司所有员工列表
	 * 
	 * @param orgId
	 * @return
	 */
	List<LoginUser> listByOrgId(String orgId);
}
