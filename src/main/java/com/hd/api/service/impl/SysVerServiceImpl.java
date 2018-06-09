package com.hd.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.hd.api.dao.SysVerMapper;
import com.hd.api.dto.AppManualDTO;
import com.hd.api.dto.SysVerDTO;
import com.hd.api.model.SysVer;
import com.hd.api.service.SysVerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysVerServiceImpl extends BaseServiceImpl<SysVer> implements SysVerService {

	@Autowired
	private SysVerMapper svMapper;

	@Override
	public SysVerDTO getLatestSysVer() {
		SysVerDTO sysVerDTO = svMapper.getLatestSysVer();
		if (StringUtils.isNotEmpty(sysVerDTO.getDescriptionStr())) {
			List<String> descList = JSON.parseArray(sysVerDTO.getDescriptionStr(), String.class);
			sysVerDTO.setDescription(descList);
		}
		return sysVerDTO;
	}

	@Override
	public AppManualDTO getAppManual(String version) {
		return svMapper.getAppManual(version);
	}

}
