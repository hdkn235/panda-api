package com.hd.api.service.impl;

import com.hd.api.dao.UserinfoMapper;
import com.hd.api.dto.RolesDTO;
import com.hd.api.dto.UserDTO;
import com.hd.api.dto.UserFullInfoDTO;
import com.hd.api.dto.UserInfoDTO;
import com.hd.api.model.OrgInfo;
import com.hd.api.model.UserRole;
import com.hd.api.model.Userinfo;
import com.hd.api.service.OrgInfoService;
import com.hd.api.service.UserInfoService;
import com.hd.api.service.UserRoleService;
import com.hd.utils.common.CommonUtil;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<Userinfo> implements UserInfoService {

	@Autowired
	private UserinfoMapper uiMapper;

	@Autowired
	private UserRoleService urService;

	@Autowired
	private OrgInfoService oiService;

	@Override
	public UserInfoDTO getUserInfoDTO(String uid) {
		return uiMapper.getMyInfo(uid);
	}

	@Override
	public Userinfo getUserInfoByPhone(String phone) {
		return uiMapper.getByPhone(phone);
	}

	@Override
	public Userinfo getInviteUserInfo(String inviteCode) {
		return uiMapper.getInviteUser(inviteCode);
	}

	@Override
	public List<UserDTO> listOrgUserInfo(String userid) {
		List<UserDTO> list = uiMapper.listOrgUserInfo(userid);
		OrgInfo orgInfo = oiService.getByUserId(userid);
		list.forEach(dto -> {
			if (dto.getUserId().equals(orgInfo.getUserId())) {
				dto.setLevelName("企业创建人");
			}
		});
		return list;
	}

	@Override
	public List<UserDTO> listUserInfo(String userid) {
		return uiMapper.listUserInfo(userid);
	}

	@Override
	public String getLoginType(String userId) {
		return uiMapper.getLoginType(userId);
	}

	@Override
	public List<UserDTO> listSuccessor(String userid) {
		return uiMapper.listSuccessor(userid);
	}

	@Override
	public List<UserDTO> listByRoleLevel(String userid, String roleLevel) {
		return uiMapper.listByRoleLevel(userid, roleLevel);
	}

	@Override
	public List<UserFullInfoDTO> getUserListByUserId(String userId) {
		return uiMapper.getUserListByUserId(userId);
	}

	@Override
	public UserFullInfoDTO getUserFullInfoByUserId(String userId) {
		return uiMapper.getUserFullInfoByUserId(userId);
	}

	@Override
	public boolean updateLevel(String userId, JSONArray roleIds) {
		boolean flag = false;
		if (roleIds != null) {
			// 如果赋予了角色 就清空之前的角色
			boolean result = urService.deleteRoleByUser(userId);
			if (result) {
				UserRole userRole = new UserRole();
				roleIds.forEach(t -> {
					String roleStr = (String) t;
					userRole.setUserRoleId(CommonUtil.createUUID());
					userRole.setRoleId(roleStr);
					userRole.setUserId(userId);
					urService.insert(userRole);
				});
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public boolean verifyCreator(String userId) {
		boolean flag = false;
		List<RolesDTO> dto = urService.listRoleByUserId(userId);
		Map<String, RolesDTO> roleMap = dto.stream().collect(Collectors.toMap(RolesDTO::getLevel, d -> d));
		if (roleMap.containsKey("AA")) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Userinfo> listByOrgId(String orgId) {
		return uiMapper.listByOrgId(orgId);
	}
}
