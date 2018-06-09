package com.hd.api.service;

import com.hd.api.dto.HomeSysMessageDTO;
import com.hd.api.dto.SystemMessageDTO;
import com.hd.api.model.SystemMessage;

import java.util.List;

public interface SystemMessageService extends BaseService<SystemMessage> {

	/**
	 * 获取首页消息
	 * 
	 * @param uid
	 */
	List<HomeSysMessageDTO> getHomeSysMessage(String uid);

	/**
	 * 获取系统消息列表
	 * 
	 * @param uid
	 * @param category
	 * @param currentPage
	 * @return
	 */
	List<SystemMessageDTO> getSysMessageInfo(String uid, String category, Integer currentPage, Integer pageSize);

	/**
	 * 获取需要推送的消息
	 * 
	 * @return
	 */
	List<SystemMessage> getPushMessages();

	/**
	 * 发送值班消息
	 * 
	 * @param toUserId
	 * @param content
	 */
	void sendDutyMsg(String fromUserId, String toUserId, String content);

	/**
	 * 发送报修单消息
	 * 
	 * @param toUserId
	 * @param content
	 */
	void sendRepairMsg(String fromUserId, String toUserId, String content, String remark);

	/**
	 * 发送报修单消息
	 * 
	 * @param toUserId
	 * @param content
	 */
	void sendRepairMsg(String fromUserId, List<String> toUserId, String content, String remark);

}
