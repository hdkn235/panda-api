package com.hd.api.service;

import com.hd.api.dto.UserDTO;
import com.hd.api.dto.UserFullInfoDTO;
import com.hd.api.dto.UserInfoDTO;
import com.hd.api.model.Userinfo;
import org.json.JSONArray;

import java.util.List;

public interface UserInfoService extends BaseService<Userinfo> {

	/**
	 * 获取用户信息Map
	 * 
	 * @param uid
	 * @return
	 */
	UserInfoDTO getUserInfoDTO(String uid);

	/**
	 * 获取用户信息通过电话
	 * 
	 * @param phone
	 * @return
	 */
	Userinfo getUserInfoByPhone(String phone);

	/**
	 * 获取用户信息通过邀请码
	 * 
	 * @param inviteCode
	 * @return
	 */
	Userinfo getInviteUserInfo(String inviteCode);

	/**
	 * 获取公司所有员工列表
	 * 
	 * @param userid
	 * @return
	 */
	List<UserDTO> listOrgUserInfo(String userid);

	/**
	 * 获取公司所有员工列表
	 * 
	 * @param userid
	 * @return
	 */
	List<UserDTO> listUserInfo(String userid);

	/**
	 * 获取登录类型
	 * 
	 * @param userId
	 * @return
	 */
	String getLoginType(String userId);

	/**
	 * 获取接班人列表
	 * 
	 * @param userid
	 * @return
	 */
	List<UserDTO> listSuccessor(String userid);

	/**
	 * 根据角色获取用户列表
	 * 
	 * @param userid
	 * @return
	 */
	List<UserDTO> listByRoleLevel(String userid, String roleLevel);

	/**
	 * 获取机构用户列表
	 * 
	 * @param userid
	 * @return
	 */
	List<UserFullInfoDTO> getUserListByUserId(String userId);

	/**
	 * 获取个人详细信息
	 * 
	 * @param userid
	 * @return
	 */
	UserFullInfoDTO getUserFullInfoByUserId(String userId);

	/**
	 * 修改客户级别
	 * 
	 * @param userId
	 * @param level
	 * @return
	 */
	boolean updateLevel(String userId, JSONArray roleIds);

	/**
	 * 验证是否企业创建人
	 * 
	 * @param userId
	 * @param level
	 * @return
	 */
	boolean verifyCreator(String userId);

	/**
	 * 获取公司所有员工列表
	 * 
	 * @param orgId
	 * @return
	 */
	List<Userinfo> listByOrgId(String orgId);
	
}
