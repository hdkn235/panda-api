package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.RolesDTO;
import com.hd.api.model.Role;
import com.hd.api.model.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

	/**
	 * 根据用户id获取用户角色列表
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> listByUserId(String userId);
	
	/**
	 * 根据用户id获取用户角色
	 * 
	 * @param userId
	 * @return
	 */
	List<RolesDTO> listRoleByUserId(String userId);

	/**
	 * 获得所有角色
	 * @return
	 */
	List<RolesDTO> getAllRoles();

	/**
	 * 根据用户添加角色
	 * @return
	 */
	boolean addRoleByUserId(String userId, String roleStr);

	/**
	 * 根据用户id删除角色
	 * @return
	 */
	int deleteRoleByUser(String userId);

}