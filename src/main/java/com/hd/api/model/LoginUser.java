package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class LoginUser {
    @Id
    @Column(name = "UserId")
    private String userId;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "LoginName")
    private String loginName;

    @Column(name = "PassWord")
    private String passWord;

    @Column(name = "LoginType")
    private Integer loginType;

    @Column(name = "UpdateDate")
    private Date updateDate;

    @Column(name = "LimitIp")
    private String limitIp;

    @Column(name = "LoginIp")
    private String loginIp;

    @Column(name = "Remark")
    private String remark;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "LastTime")
    private Date lastTime;

    @Column(name = "ClientId")
    private String clientId;

    public LoginUser(String userId, String phone, String loginName, String passWord, Integer loginType, Date updateDate, String limitIp, String loginIp, String remark, Integer status, Date lastTime, String clientId) {
        this.userId = userId;
        this.phone = phone;
        this.loginName = loginName;
        this.passWord = passWord;
        this.loginType = loginType;
        this.updateDate = updateDate;
        this.limitIp = limitIp;
        this.loginIp = loginIp;
        this.remark = remark;
        this.status = status;
        this.lastTime = lastTime;
        this.clientId = clientId;
    }

    public LoginUser() {
        super();
    }

    /**
     * @return UserId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return Phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return LoginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * @return PassWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    /**
     * @return LoginType
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * @return UpdateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return LimitIp
     */
    public String getLimitIp() {
        return limitIp;
    }

    /**
     * @param limitIp
     */
    public void setLimitIp(String limitIp) {
        this.limitIp = limitIp == null ? null : limitIp.trim();
    }

    /**
     * @return LoginIp
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * @param loginIp
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * @return Remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return LastTime
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * @param lastTime
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * @return ClientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }
}