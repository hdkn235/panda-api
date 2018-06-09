package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class DictClass {
    @Id
    @Column(name = "DictClassId")
    private String dictClassId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Code")
    private String code;

    @Column(name = "Remark")
    private String remark;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "CreateTime")
    private Date createTime;

    public DictClass(String dictClassId, String name, String code, String remark, Integer status, Date createTime) {
        this.dictClassId = dictClassId;
        this.name = name;
        this.code = code;
        this.remark = remark;
        this.status = status;
        this.createTime = createTime;
    }

    public DictClass() {
        super();
    }

    /**
     * @return DictClassId
     */
    public String getDictClassId() {
        return dictClassId;
    }

    /**
     * @param dictClassId
     */
    public void setDictClassId(String dictClassId) {
        this.dictClassId = dictClassId == null ? null : dictClassId.trim();
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return Code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
}