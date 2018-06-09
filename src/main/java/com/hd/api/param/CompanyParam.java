package com.hd.api.param;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CompanyParam {

	@NotEmpty
	private String orgName;

	private String inviteCode;

	@NotEmpty
	private String userName;

	@NotEmpty
	private String phone;

	@NotEmpty
	private String verifyCode;

	@NotEmpty
	@Size(min = 6)
	private String passWord;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
