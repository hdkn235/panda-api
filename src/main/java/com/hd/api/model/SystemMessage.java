package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class SystemMessage {
    @Id
    @Column(name = "SystemMessageId")
    private String systemMessageId;

    @Column(name = "Type")
    private String type;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "ToUserId")
    private String toUserId;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Category")
    private String category;

    @Column(name = "FromUserId")
    private String fromUserId;

    @Column(name = "Remark")
    private String remark;

    @Column(name = "Message")
    private String message;

    public SystemMessage(String systemMessageId, String type, Date createTime, String toUserId, Integer status, String category, String fromUserId, String remark, String message) {
        this.systemMessageId = systemMessageId;
        this.type = type;
        this.createTime = createTime;
        this.toUserId = toUserId;
        this.status = status;
        this.category = category;
        this.fromUserId = fromUserId;
        this.remark = remark;
        this.message = message;
    }

    public SystemMessage() {
        super();
    }

    /**
     * @return SystemMessageId
     */
    public String getSystemMessageId() {
        return systemMessageId;
    }

    /**
     * @param systemMessageId
     */
    public void setSystemMessageId(String systemMessageId) {
        this.systemMessageId = systemMessageId == null ? null : systemMessageId.trim();
    }

    /**
     * @return Type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * @return ToUserId
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * @param toUserId
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
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
     * @return Category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * @return FromUserId
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * @param fromUserId
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
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
     * @return Message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}