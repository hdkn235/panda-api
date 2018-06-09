package com.hd.api.dto;

import java.util.List;

public class LoginDTO {

	private String UserId;

	private String LoginType;

	private String Token;
	
	private List<String> Roles;

	public List<String> getRoles() {
		return Roles;
	}

	public void setRoles(List<String> roles) {
		Roles = roles;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		this.UserId = userId;
	}

	public String getLoginType() {
		return LoginType;
	}

	public void setLoginType(String loginType) {
		this.LoginType = loginType;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		this.Token = token;
	}
}
