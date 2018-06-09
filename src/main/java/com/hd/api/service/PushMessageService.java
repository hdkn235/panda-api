package com.hd.api.service;

import com.hd.api.constant.PushMessageConst;

import java.util.List;

public interface PushMessageService {

	/**
	 * 推送系统消息
	 * 
	 * @param cid
	 * @param title
	 * @param text
	 * @param category
	 * @return
	 */
	boolean pushSystemMsg(String cid, String title, String text, String category);

	/**
	 * 推送维修消息
	 * 
	 * @param cid
	 * @param title
	 * @param text
	 * @return
	 */
	boolean pushRepairMsg(String cid, String title, String text, String message);

	/**
	 * 发送透传通知
	 * 
	 * @param trans
	 * @param cid
	 * @param message
	 * @return
	 */
	boolean pushTransmission(PushMessageConst.TransActionEnum trans, String cid, Object message);

	/**
	 * 发送透传通知
	 * 
	 * @param trans
	 * @param cids
	 * @param message
	 * @return
	 */
	boolean pushTransmission(PushMessageConst.TransActionEnum trans, List<String> cids, Object message);

}
