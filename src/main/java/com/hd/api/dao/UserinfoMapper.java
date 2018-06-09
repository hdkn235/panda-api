package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.UserDTO;
import com.hd.api.dto.UserFullInfoDTO;
import com.hd.api.dto.UserInfoDTO;
import com.hd.api.model.Userinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoMapper extends BaseMapper<Userinfo> {
	/**
	 * 获取我的信息
	 * 
	 * @param userid
	 * @return
	 */
	UserInfoDTO getMyInfo(String userid);

	/**
	 * 根据手机号获取用户信息
	 * 
	 * @param phone
	 * @return
	 */
	Userinfo getByPhone(String phone);

	/**
	 * 获取用户信息通过邀请码
	 * 
	 * @param phone
	 * @return
	 */
	Userinfo getInviteUser(String phone);

	/**
	 * 获取用户列表
	 * 
	 * @param userid
	 * @return
	 */
	List<UserDTO> listUserInfo(String userid);

	/**
	 * 获取用户列表
	 * 
	 * @param userid
	 * @return
	 */
	List<UserDTO> listOrgUserInfo(String userid);

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
	List<UserDTO> listByRoleLevel(@Param("userid") String userid, @Param("roleLevel") String roleLevel);

	/**
	 * 获取机构人员表
	 * 
	 * @param userId
	 * @return
	 */
	List<UserFullInfoDTO> getUserListByUserId(@Param("userId") String userId);

	/**
	 * 获取个人信息详细信息
	 * 
	 * @param userId
	 * @return
	 */
	UserFullInfoDTO getUserFullInfoByUserId(@Param("userId") String userId);

	/**
	 * 获取公司所有员工列表
	 * 
	 * @param orgId
	 * @return
	 */
	List<Userinfo> listByOrgId(String orgId);
}