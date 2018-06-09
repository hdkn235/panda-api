package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class Dict {
    @Id
    @Column(name = "DictId")
    private String dictId;

    @Column(name = "Code")
    private String code;

    @Column(name = "Value")
    private String value;

    @Column(name = "DictClassId")
    private String dictClassId;

    @Column(name = "OrderNumber")
    private Integer orderNumber;

    @Column(name = "Remark")
    private String remark;

    @Column(name = "Status")
    private Integer status;

    @Column(name = "CreateTime")
    private Date createTime;

    public Dict(String dictId, String code, String value, String dictClassId, Integer orderNumber, String remark, Integer status, Date createTime) {
        this.dictId = dictId;
        this.code = code;
        this.value = value;
        this.dictClassId = dictClassId;
        this.orderNumber = orderNumber;
        this.remark = remark;
        this.status = status;
        this.createTime = createTime;
    }

    public Dict() {
        super();
    }

    /**
     * @return DictId
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * @param dictId
     */
    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
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
     * @return Value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
     * @return OrderNumber
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
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