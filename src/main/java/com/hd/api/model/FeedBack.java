package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class FeedBack {
    @Id
    @Column(name = "FeedBackId")
    private String feedBackId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "Content")
    private String content;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "ContactInfo")
    private String contactInfo;

    public FeedBack(String feedBackId, String userId, String content, Date createTime, String contactInfo) {
        this.feedBackId = feedBackId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
        this.contactInfo = contactInfo;
    }

    public FeedBack() {
        super();
    }

    /**
     * @return FeedBackId
     */
    public String getFeedBackId() {
        return feedBackId;
    }

    /**
     * @param feedBackId
     */
    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId == null ? null : feedBackId.trim();
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
     * @return Content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * @return ContactInfo
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }
}