package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class OptLog {
    @Id
    @Column(name = "LogId")
    private String logId;

    @Column(name = "AppName")
    private String appName;

    @Column(name = "LogType")
    private Integer logType;

    @Column(name = "RequestIp")
    private String requestIp;

    @Column(name = "RequestUri")
    private String requestUri;

    @Column(name = "RequestMethod")
    private String requestMethod;

    @Column(name = "RequestParams")
    private String requestParams;

    @Column(name = "MethodName")
    private String methodName;

    @Column(name = "MethodDesc")
    private String methodDesc;

    @Column(name = "ExceptionCode")
    private String exceptionCode;

    @Column(name = "CreateUser")
    private String createUser;

    @Column(name = "CreateTime")
    private Date createTime;

    @Column(name = "ResponseStatus")
    private String responseStatus;

    @Column(name = "ResponseContent")
    private String responseContent;

    @Column(name = "ExceptionDetail")
    private String exceptionDetail;

    public OptLog(String logId, String appName, Integer logType, String requestIp, String requestUri, String requestMethod, String requestParams, String methodName, String methodDesc, String exceptionCode, String createUser, Date createTime, String responseStatus, String responseContent, String exceptionDetail) {
        this.logId = logId;
        this.appName = appName;
        this.logType = logType;
        this.requestIp = requestIp;
        this.requestUri = requestUri;
        this.requestMethod = requestMethod;
        this.requestParams = requestParams;
        this.methodName = methodName;
        this.methodDesc = methodDesc;
        this.exceptionCode = exceptionCode;
        this.createUser = createUser;
        this.createTime = createTime;
        this.responseStatus = responseStatus;
        this.responseContent = responseContent;
        this.exceptionDetail = exceptionDetail;
    }

    public OptLog() {
        super();
    }

    /**
     * @return LogId
     */
    public String getLogId() {
        return logId;
    }

    /**
     * @param logId
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * @return AppName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * @return LogType
     */
    public Integer getLogType() {
        return logType;
    }

    /**
     * @param logType
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    /**
     * @return RequestIp
     */
    public String getRequestIp() {
        return requestIp;
    }

    /**
     * @param requestIp
     */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    /**
     * @return RequestUri
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * @param requestUri
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    /**
     * @return RequestMethod
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * @param requestMethod
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
    }

    /**
     * @return RequestParams
     */
    public String getRequestParams() {
        return requestParams;
    }

    /**
     * @param requestParams
     */
    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams == null ? null : requestParams.trim();
    }

    /**
     * @return MethodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * @return MethodDesc
     */
    public String getMethodDesc() {
        return methodDesc;
    }

    /**
     * @param methodDesc
     */
    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc == null ? null : methodDesc.trim();
    }

    /**
     * @return ExceptionCode
     */
    public String getExceptionCode() {
        return exceptionCode;
    }

    /**
     * @param exceptionCode
     */
    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode == null ? null : exceptionCode.trim();
    }

    /**
     * @return CreateUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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
     * @return ResponseStatus
     */
    public String getResponseStatus() {
        return responseStatus;
    }

    /**
     * @param responseStatus
     */
    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus == null ? null : responseStatus.trim();
    }

    /**
     * @return ResponseContent
     */
    public String getResponseContent() {
        return responseContent;
    }

    /**
     * @param responseContent
     */
    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent == null ? null : responseContent.trim();
    }

    /**
     * @return ExceptionDetail
     */
    public String getExceptionDetail() {
        return exceptionDetail;
    }

    /**
     * @param exceptionDetail
     */
    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail == null ? null : exceptionDetail.trim();
    }
}