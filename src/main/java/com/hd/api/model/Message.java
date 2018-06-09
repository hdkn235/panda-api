package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class Message {
    @Id
    @Column(name = "MessageId")
    private String messageid;

    @Column(name = "UserId")
    private String userid;

    @Column(name = "Type")
    private String type;

    @Column(name = "ToMessageId")
    private String tomessageid;

    @Column(name = "CommentNumber")
    private String commentnumber;

    @Column(name = "WelldoneNumber")
    private String welldonenumber;

    @Column(name = "MessageInfo")
    private String messageinfo;

    @Column(name = "CreateTime")
    private Date createtime;

    @Column(name = "ImageUrl")
    private String imageurl;

    public Message(String messageid, String userid, String type, String tomessageid, String commentnumber, String welldonenumber, String messageinfo, Date createtime, String imageurl) {
        this.messageid = messageid;
        this.userid = userid;
        this.type = type;
        this.tomessageid = tomessageid;
        this.commentnumber = commentnumber;
        this.welldonenumber = welldonenumber;
        this.messageinfo = messageinfo;
        this.createtime = createtime;
        this.imageurl = imageurl;
    }

    public Message() {
        super();
    }

    /**
     * @return MessageId
     */
    public String getMessageid() {
        return messageid;
    }

    /**
     * @param messageid
     */
    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    /**
     * @return UserId
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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
     * @return ToMessageId
     */
    public String getTomessageid() {
        return tomessageid;
    }

    /**
     * @param tomessageid
     */
    public void setTomessageid(String tomessageid) {
        this.tomessageid = tomessageid == null ? null : tomessageid.trim();
    }

    /**
     * @return CommentNumber
     */
    public String getCommentnumber() {
        return commentnumber;
    }

    /**
     * @param commentnumber
     */
    public void setCommentnumber(String commentnumber) {
        this.commentnumber = commentnumber == null ? null : commentnumber.trim();
    }

    /**
     * @return WelldoneNumber
     */
    public String getWelldonenumber() {
        return welldonenumber;
    }

    /**
     * @param welldonenumber
     */
    public void setWelldonenumber(String welldonenumber) {
        this.welldonenumber = welldonenumber == null ? null : welldonenumber.trim();
    }

    /**
     * @return MessageInfo
     */
    public String getMessageinfo() {
        return messageinfo;
    }

    /**
     * @param messageinfo
     */
    public void setMessageinfo(String messageinfo) {
        this.messageinfo = messageinfo == null ? null : messageinfo.trim();
    }

    /**
     * @return CreateTime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return ImageUrl
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * @param imageurl
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }
}