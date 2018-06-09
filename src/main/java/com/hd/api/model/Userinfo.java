package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "UserInfo")
public class Userinfo {
    @Id
    @Column(name = "UserId")
    private String userid;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "UserName")
    private String username;

    @Column(name = "Email")
    private String email;

    @Column(name = "Location")
    private String location;

    @Column(name = "City")
    private String city;

    @Column(name = "School")
    private String school;

    @Column(name = "Interest")
    private String interest;

    @Column(name = "OnLineTime")
    private String onlinetime;

    @Column(name = "Level")
    private String level;

    @Column(name = "Credits")
    private String credits;

    @Column(name = "OrgId")
    private String orgid;

    @Column(name = "SuperiorId")
    private String superiorid;

    @Column(name = "AvatarUrl")
    private String avatarurl;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "CreateTime")
    private Date createtime;

    @Column(name = "IsTemp")
    private Integer istemp;

    public Userinfo(String userid, String phone, String username, String email, String location, String city, String school, String interest, String onlinetime, String level, String credits, String orgid, String superiorid, String avatarurl, String gender, Date createtime, Integer istemp) {
        this.userid = userid;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.location = location;
        this.city = city;
        this.school = school;
        this.interest = interest;
        this.onlinetime = onlinetime;
        this.level = level;
        this.credits = credits;
        this.orgid = orgid;
        this.superiorid = superiorid;
        this.avatarurl = avatarurl;
        this.gender = gender;
        this.createtime = createtime;
        this.istemp = istemp;
    }

    public Userinfo() {
        super();
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
     * @return UserName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
     * @return School
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school
     */
    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    /**
     * @return Interest
     */
    public String getInterest() {
        return interest;
    }

    /**
     * @param interest
     */
    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    /**
     * @return OnLineTime
     */
    public String getOnlinetime() {
        return onlinetime;
    }

    /**
     * @param onlinetime
     */
    public void setOnlinetime(String onlinetime) {
        this.onlinetime = onlinetime == null ? null : onlinetime.trim();
    }

    /**
     * @return Level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * @return Credits
     */
    public String getCredits() {
        return credits;
    }

    /**
     * @param credits
     */
    public void setCredits(String credits) {
        this.credits = credits == null ? null : credits.trim();
    }

    /**
     * @return OrgId
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * @param orgid
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    /**
     * @return SuperiorId
     */
    public String getSuperiorid() {
        return superiorid;
    }

    /**
     * @param superiorid
     */
    public void setSuperiorid(String superiorid) {
        this.superiorid = superiorid == null ? null : superiorid.trim();
    }

    /**
     * @return AvatarUrl
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * @param avatarurl
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    /**
     * @return Gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
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
     * @return IsTemp
     */
    public Integer getIstemp() {
        return istemp;
    }

    /**
     * @param istemp
     */
    public void setIstemp(Integer istemp) {
        this.istemp = istemp;
    }
}