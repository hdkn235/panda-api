package com.hd.api.service;

import com.hd.api.dto.FunStatsDTO;
import com.hd.api.model.OptLog;

import java.util.List;

public interface OptLogService extends BaseService<OptLog> {

	/**
	 * 根据用户ID,进行功能统计
	 * 
	 * @param userId
	 * @return
	 */
	List<FunStatsDTO> selectFunStatsByUserId(String userId, String beginTime, String endTime);

	/**
	 * 查询功能的访问者
	 * 
	 * @param methodName
	 * @param methodDesc
	 * @param createTime
	 * @return
	 */
	List<FunStatsDTO> selectFeatureVisitor(String methodName, String methodDesc, String beginTime, String endTime);
}
