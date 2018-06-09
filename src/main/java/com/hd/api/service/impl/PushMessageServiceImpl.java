package com.hd.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.hd.api.aspect.ServiceLog;
import com.hd.api.constant.PushMessageConst;
import com.hd.api.service.LoginUserService;
import com.hd.api.service.PushMessageService;
import com.hd.api.thirdly.AppPushManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushMessageServiceImpl implements PushMessageService {

	@Autowired
	private AppPushManager pushManager;

	@Autowired
	private LoginUserService luService;

	@Override
	@ServiceLog(description = "发送透传消息")
	public boolean pushTransmission(PushMessageConst.TransActionEnum trans, String cid, Object message) {
		JSONObject contentObj = new JSONObject();
		contentObj.put("Action", trans.toString());
		contentObj.put("Message", message == null ? "" : message);
		TransmissionTemplate tt = pushManager.getTransmissionTemplate(contentObj.toJSONString());
		return pushManager.pushToSingle(cid, tt);
	}

	@Override
	@ServiceLog(description = "发送透传消息")
	public boolean pushTransmission(PushMessageConst.TransActionEnum trans, List<String> cids, Object message) {
		JSONObject contentObj = new JSONObject();
		contentObj.put("Action", trans.toString());
		contentObj.put("Message", message == null ? "" : message);
		TransmissionTemplate tt = pushManager.getTransmissionTemplate(contentObj.toJSONString());
		return pushManager.pushToList(cids, tt);
	}

	@Override
	public boolean pushSystemMsg(String cid, String title, String text, String message) {
		JSONObject contentObj = new JSONObject();
		contentObj.put("Action", "OpenMessage");
		contentObj.put("Message", StringUtils.defaultIfBlank(message, ""));
		NotificationTemplate tmpl = pushManager.getNotificationTemplate(title, text, contentObj.toJSONString());
		return pushManager.pushToSingle(cid, tmpl);
	}

	@Override
	public boolean pushRepairMsg(String cid, String title, String text, String message) {
		JSONObject contentObj = new JSONObject();
		contentObj.put("Action", "OpenRepairInfo");
		contentObj.put("Message", StringUtils.defaultIfBlank(message, ""));
		NotificationTemplate tmpl = pushManager.getNotificationTemplate(title, text, contentObj.toJSONString());
		return pushManager.pushToSingle(cid, tmpl);
	}

}
