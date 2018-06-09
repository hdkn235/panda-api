package com.hd.api.dto;

public class SystemMessageDTO {

	private String SystemMessageId;
	
	private String Message;
	
	private String CreateTime;

	public String getSystemMessageId() {
		return SystemMessageId;
	}

	public void setSystemMessageId(String systemMessageId) {
		SystemMessageId = systemMessageId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
}
