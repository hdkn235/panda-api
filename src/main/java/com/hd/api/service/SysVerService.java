package com.hd.api.service;

import com.hd.api.dto.AppManualDTO;
import com.hd.api.dto.SysVerDTO;
import com.hd.api.model.SysVer;

public interface SysVerService extends BaseService<SysVer> {

	/**
	 * 获取APP最新版本
	 * 
	 * @return
	 */
	SysVerDTO getLatestSysVer();

	/**
	 * 获取APP使用手册url
	 * 
	 * @return
	 */
	AppManualDTO getAppManual(String version);
}
