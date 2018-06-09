package com.hd.api.dto;

public class UserDTO {

	/**
	 * 用户ID
	 */
	private String UserId;
	/**
	 * 用户昵称
	 */
	private String UserName;
	/**
	 * 手机号
	 */
	private String Phone;
	/**
	 * 级别 A 超级用户 B 管理员 C 普通员工
	 */
	private String Level;
	/**
	 * 级别名称
	 */
	private String LevelName;
	/**
	 * 头像url
	 */
	private String AvatarUrl;

	public String getAvatarUrl() {
		return AvatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		AvatarUrl = avatarUrl;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getLevelName() {
		return LevelName;
	}

	public void setLevelName(String levelName) {
		LevelName = levelName;
	}
}
