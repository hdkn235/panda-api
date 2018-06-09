package com.hd.api.thirdly;

import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import java.util.List;

public interface AppPushManager {
	/**
	 * 对单个用户推送消息
	 * 
	 * @param cid
	 *            透传内容
	 */
	boolean pushToSingle(String cid, ITemplate template);

	/**
	 * 对指定列表用户推送消息
	 * 
	 * @param cids
	 *            透传的内容
	 * @throws Exception
	 */
	boolean pushToList(List<String> cids, ITemplate template);

	/**
	 * 对指定应用群推消息
	 * 
	 * @param groupName
	 */
	boolean pushToApp(String groupName, ITemplate template);

	/**
	 * 点击通知打开应用模板
	 * 
	 * @param title
	 * @param text
	 * @param content
	 * @return
	 */
	NotificationTemplate getNotificationTemplate(String title, String text, String content);

	/**
	 * 点击通知打开网页模板
	 * 
	 * @param title
	 * @param text
	 * @return
	 */
	LinkTemplate getLinkTemplate(String title, String text, String url);

	/**
	 * 透传消息模版
	 * 
	 * @param content
	 * @return
	 */
	TransmissionTemplate getTransmissionTemplate(String content);

	/**
	 * 点击通知弹窗下载模板
	 * 
	 * @param notyTitle
	 * @param notyContent
	 * @param popTitle
	 * @param popContent
	 * @param loadTitle
	 * @param loadUrl
	 * @return
	 */
	NotyPopLoadTemplate getNotyPopLoadTemplate(String notyTitle, String notyContent, String popTitle, String popContent,
                                               String loadTitle, String loadUrl);
}
