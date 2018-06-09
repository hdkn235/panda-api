package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.HomeSysMessageDTO;
import com.hd.api.dto.SystemMessageDTO;
import com.hd.api.model.SystemMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemMessageMapper extends BaseMapper<SystemMessage> {
	/**
	 * 获取消息首页信息
	 * 
	 * @param userid
	 * @return
	 */
	List<HomeSysMessageDTO> getHomeSysMessage(String userid);

	/**
	 * 获取分类最新消息
	 * 
	 * @param userid
	 * @param category
	 * @return
	 */
	HomeSysMessageDTO getNewSysMessage(@Param("userid") String userid, @Param("category") String category);
	
	/**
	 * 获取分类最新消息
	 * 
	 * @param userid
	 * @param category
	 * @return
	 */
	List<HomeSysMessageDTO> listLatelySysMessage(@Param("userid") String userid);

	/**
	 * 获取朋友发送的新消息
	 * 
	 * @param userid
	 * @return
	 */
	List<Map<String, Object>> getNewFriendMessage(String userid);

	/**
	 * 更新消息已读状态
	 * 
	 * @param userid
	 * @param categorys
	 * @return
	 */
	int updateUnReadMessage(@Param("userid") String userid, @Param("categorys") String[] categorys);

	/**
	 * 获取消息列表
	 * 
	 * @param touserid
	 * @param category
	 * @param startNum
	 * @param endNum
	 * @return
	 */
	List<SystemMessageDTO> getSysMessageInfo(@Param("userid") String touserid, @Param("category") String category);

	/**
	 * 获取需要推送的消息
	 * 
	 * @return
	 */
	List<SystemMessage> getPushMessages();
}