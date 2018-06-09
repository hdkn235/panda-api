package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.AppManualDTO;
import com.hd.api.dto.SysVerDTO;
import com.hd.api.model.SysVer;

public interface SysVerMapper extends BaseMapper<SysVer> {

	/**
	 * 获取最新APP版本
	 * 
	 * @return
	 */
	SysVerDTO getLatestSysVer();

	/**
	 * 获取用户手册
	 * 
	 * @param version
	 * @return
	 */
	AppManualDTO getAppManual(String version);
}