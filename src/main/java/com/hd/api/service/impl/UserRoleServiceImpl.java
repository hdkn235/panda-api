package com.hd.api.service.impl;

import com.hd.api.dao.UserRoleMapper;
import com.hd.api.dto.RolesDTO;
import com.hd.api.model.Role;
import com.hd.api.model.UserRole;
import com.hd.api.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

	@Autowired
	private UserRoleMapper urMapper;

	@Override
	public List<Role> listByUserId(String userId) {
		return urMapper.listByUserId(userId);
	}

	@Override
	public List<String> listLevelByUserId(String userId) {
		List<Role> roleList = urMapper.listByUserId(userId);
		return roleList.stream().map(Role::getLevel).collect(Collectors.toList());
	}

	@Override
	public List<RolesDTO> listRolesByUser(String userId, String selectId) {
		List<RolesDTO> dto = urMapper.listRoleByUserId(userId);
		Map<String, RolesDTO> roleMap = dto.stream().collect(Collectors.toMap(RolesDTO::getLevel, d -> d));
		if (roleMap.containsKey("AAA")) {
			// 查询所有
			List<RolesDTO> allDTO = urMapper.getAllRoles();
			Map<String, RolesDTO> allRoleMap = allDTO.stream().collect(Collectors.toMap(RolesDTO::getRoleid, d -> d));
			List<Role> roles = urMapper.listByUserId(selectId);
			roles.forEach(role -> {
				allRoleMap.get(role.getRoleId()).setSign("1");
			});
			return allDTO;
		}
		return null;
	}

	@Override
	public List<RolesDTO> listRoleByUserId(String userId) {
		return urMapper.listRoleByUserId(userId);
	}

	@Override
	public boolean deleteRoleByUser(String userId) {
		return urMapper.deleteRoleByUser(userId) >= 0;
	}

}
