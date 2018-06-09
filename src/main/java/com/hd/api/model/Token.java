package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class Token {
    @Id
    @Column(name = "TokenId")
    private String tokenId;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "Expiration")
    private Date expiration;

    public Token(String tokenId, String userId, Date createTime, Integer status, Date expiration) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.createTime = createTime;
        this.status = status;
        this.expiration = expiration;
    }

    public Token() {
        super();
    }

    /**
     * @return TokenId
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @param tokenId
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId == null ? null : tokenId.trim();
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
     * @return Expiration
     */
    public Date getExpiration() {
        return expiration;
    }

    /**
     * @param expiration
     */
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}