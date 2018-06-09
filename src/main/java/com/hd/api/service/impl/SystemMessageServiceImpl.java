package com.hd.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.hd.api.constant.SystemMsgConst.MsgCategoryEnum;
import com.hd.api.constant.SystemMsgConst.MsgStatusEnum;
import com.hd.api.constant.SystemMsgConst.MsgTypeEnum;
import com.hd.api.dao.SystemMessageMapper;
import com.hd.api.dto.HomeSysMessageDTO;
import com.hd.api.dto.SystemMessageDTO;
import com.hd.api.model.SystemMessage;
import com.hd.api.service.SystemMessageService;
import com.hd.utils.common.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SystemMessageServiceImpl extends BaseServiceImpl<SystemMessage> implements SystemMessageService {

	@Autowired
	private SystemMessageMapper sysMsgMapper;

	@Override
	public List<HomeSysMessageDTO> getHomeSysMessage(String uid) {
		List<HomeSysMessageDTO> newMsgs = sysMsgMapper.listLatelySysMessage(uid);
		if (CollectionUtils.isEmpty(newMsgs)) {
			return null;
		}
		List<HomeSysMessageDTO> msgs = sysMsgMapper.getHomeSysMessage(uid);
		if (CollectionUtils.isNotEmpty(msgs)) {
			Map<String, String> msgMap = msgs.stream()
				.collect(Collectors.toMap(HomeSysMessageDTO::getCategory, HomeSysMessageDTO::getCounts));
			newMsgs.forEach(msg -> {
				if (msgMap.containsKey(msg.getCategory())) {
					msg.setCounts(msgMap.get(msg.getCategory()));
				}
			});
		}
		return newMsgs;
	}

	@Override
	public List<SystemMessageDTO> getSysMessageInfo(String uid, String category, Integer currentPage,
                                                    Integer pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<SystemMessageDTO> msgs = sysMsgMapper.getSysMessageInfo(uid, category);
		if (!msgs.isEmpty()) {
			// 已读消息
			sysMsgMapper.updateUnReadMessage(uid, new String[] { category });
		}
		return msgs;
	}

	@Override
	public List<SystemMessage> getPushMessages() {
		return sysMsgMapper.getPushMessages();
	}

	@Override
	public void sendRepairMsg(String fromUserId, String toUserId, String content, String remark) {
		SystemMessage msg = new SystemMessage();
		msg.setSystemMessageId(CommonUtil.createUUID());
		msg.setType(MsgTypeEnum.RECORD.toString());
		msg.setCategory(MsgCategoryEnum.REPAIR.toString());
		msg.setCreateTime(new Date());
		msg.setStatus(MsgStatusEnum.UN_SEND.toInt());
		msg.setFromUserId(fromUserId);
		msg.setToUserId(toUserId);
		msg.setMessage(content);
		msg.setRemark(remark);
		insert(msg);
	}

	@Override
	public void sendRepairMsg(String fromUserId, List<String> toUserIdList, String content, String remark) {
		if (CollectionUtils.isEmpty(toUserIdList)) {
			return;
		}
		for (String toUserId : toUserIdList) {
			sendRepairMsg(fromUserId, toUserId, content, remark);
		}
	}

	@Override
	public void sendDutyMsg(String fromUserId, String toUserId, String content) {
		SystemMessage msg = new SystemMessage();
		msg.setSystemMessageId(CommonUtil.createUUID());
		msg.setType(MsgTypeEnum.RECORD.toString());
		msg.setCategory(MsgCategoryEnum.DUTY.toString());
		msg.setCreateTime(new Date());
		msg.setStatus(MsgStatusEnum.UN_SEND.toInt());
		msg.setFromUserId(fromUserId);
		msg.setToUserId(toUserId);
		msg.setMessage(content);
		insert(msg);
	}

}
