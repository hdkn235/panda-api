package com.hd.api.dto;

public class RolesDTO {

	
	/**
	 * 角色ID
	 */
	private String roleId;
	
	/**
	 * 角色命名
	 */
	private String roleName;
	
	/**
	 * 角色级别
	 */
	private String level;
	
	private String sign;
	
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRoleid() {
		return roleId;
	}

	public void setRoleid(String roleid) {
		this.roleId = roleid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
