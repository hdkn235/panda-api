package com.hd.api.dto;

public class HomeSysMessageDTO {

	private String Category = "";

	private String Counts = "";

	private String NewMessage = "";

	private String SendTime = "";

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getCounts() {
		return Counts;
	}

	public void setCounts(String counts) {
		Counts = counts;
	}

	public String getNewMessage() {
		return NewMessage;
	}

	public void setNewMessage(String newMessage) {
		NewMessage = newMessage;
	}

	public String getSendTime() {
		return SendTime;
	}

	public void setSendTime(String sendTime) {
		SendTime = sendTime;
	}

}
