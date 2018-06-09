package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class SysVer {
    @Id
    @Column(name = "SysVerId")
    private String sysVerId;

    @Column(name = "Version")
    private String version;

    @Column(name = "AppUrl")
    private String appUrl;

    @Column(name = "Description")
    private String description;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "ManualUrl")
    private String manualUrl;

    public SysVer(String sysVerId, String version, String appUrl, String description, Date createTime, String manualUrl) {
        this.sysVerId = sysVerId;
        this.version = version;
        this.appUrl = appUrl;
        this.description = description;
        this.createTime = createTime;
        this.manualUrl = manualUrl;
    }

    public SysVer() {
        super();
    }

    /**
     * @return SysVerId
     */
    public String getSysVerId() {
        return sysVerId;
    }

    /**
     * @param sysVerId
     */
    public void setSysVerId(String sysVerId) {
        this.sysVerId = sysVerId == null ? null : sysVerId.trim();
    }

    /**
     * @return Version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * @return AppUrl
     */
    public String getAppUrl() {
        return appUrl;
    }

    /**
     * @param appUrl
     */
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl == null ? null : appUrl.trim();
    }

    /**
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
     * @return ManualUrl
     */
    public String getManualUrl() {
        return manualUrl;
    }

    /**
     * @param manualUrl
     */
    public void setManualUrl(String manualUrl) {
        this.manualUrl = manualUrl == null ? null : manualUrl.trim();
    }
}