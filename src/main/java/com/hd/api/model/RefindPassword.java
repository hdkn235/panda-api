package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class RefindPassword {
    @Id
    @Column(name = "RefindId")
    private String refindId;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "VerifyCode")
    private String verifyCode;

    @Column(name = "RefindTime")
    private Date refindTime;

    @Column(name = "Status")
    private Integer status;

    public RefindPassword(String refindId, String phone, String verifyCode, Date refindTime, Integer status) {
        this.refindId = refindId;
        this.phone = phone;
        this.verifyCode = verifyCode;
        this.refindTime = refindTime;
        this.status = status;
    }

    public RefindPassword() {
        super();
    }

    /**
     * @return RefindId
     */
    public String getRefindId() {
        return refindId;
    }

    /**
     * @param refindId
     */
    public void setRefindId(String refindId) {
        this.refindId = refindId == null ? null : refindId.trim();
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
     * @return VerifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode == null ? null : verifyCode.trim();
    }

    /**
     * @return RefindTime
     */
    public Date getRefindTime() {
        return refindTime;
    }

    /**
     * @param refindTime
     */
    public void setRefindTime(Date refindTime) {
        this.refindTime = refindTime;
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
}