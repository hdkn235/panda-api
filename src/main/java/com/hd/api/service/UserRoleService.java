package com.hd.api.service;

import com.hd.api.dto.RolesDTO;
import com.hd.api.model.Role;
import com.hd.api.model.UserRole;

import java.util.List;

public interface UserRoleService extends BaseService<UserRole> {

	/**
	 * 根据用户id获取用户角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> listByUserId(String userId);

	/**
	 * 根据用户id获取用户角色等级
	 * 
	 * @param userId
	 * @return
	 */
	List<String> listLevelByUserId(String userId);

	/**
	 * 根据用户id获取用户角色列表
	 * 
	 * @param userId
	 * @return
	 */
	List<RolesDTO> listRolesByUser(String userId, String selectId);

	/**
	 * 根据用户id获取用户角色
	 * 
	 * @param userId
	 * @return
	 */
	List<RolesDTO> listRoleByUserId(String userId);
	
	/**
	 * 根据用户id删除角色
	 * @return
	 */
	boolean deleteRoleByUser(String userId);
}
