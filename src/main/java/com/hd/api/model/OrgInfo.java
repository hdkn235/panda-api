package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class OrgInfo {
    @Id
    @Column(name = "OrgId")
    private String orgId;

    @Column(name = "OrgName")
    private String orgName;

    @Column(name = "CustomType")
    private String customType;

    @Column(name = "Owner")
    private String owner;

    @Column(name = "Branch")
    private String branch;

    @Column(name = "SuperiorId")
    private String superiorId;

    @Column(name = "Location")
    private String location;

    @Column(name = "City")
    private String city;

    @Column(name = "Remark")
    private String remark;

    @Column(name = "InviteCode")
    private String inviteCode;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "UserId")
    private String userId;

    public OrgInfo(String orgId, String orgName, String customType, String owner, String branch, String superiorId, String location, String city, String remark, String inviteCode, Date createTime, Integer status, String userId) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.customType = customType;
        this.owner = owner;
        this.branch = branch;
        this.superiorId = superiorId;
        this.location = location;
        this.city = city;
        this.remark = remark;
        this.inviteCode = inviteCode;
        this.createTime = createTime;
        this.status = status;
        this.userId = userId;
    }

    public OrgInfo() {
        super();
    }

    /**
     * @return OrgId
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * @return OrgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * @return CustomType
     */
    public String getCustomType() {
        return customType;
    }

    /**
     * @param customType
     */
    public void setCustomType(String customType) {
        this.customType = customType == null ? null : customType.trim();
    }

    /**
     * @return Owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * @return Branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * @return SuperiorId
     */
    public String getSuperiorId() {
        return superiorId;
    }

    /**
     * @param superiorId
     */
    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId == null ? null : superiorId.trim();
    }

    /**
     * @return Location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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
     * @return InviteCode
     */
    public String getInviteCode() {
        return inviteCode;
    }

    /**
     * @param inviteCode
     */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    /**
     * @return CreateTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}