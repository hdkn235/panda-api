package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.dto.FunStatsDTO;
import com.hd.api.model.OptLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptLogMapper extends BaseMapper<OptLog> {

	/**
	 * 根据用户ID,进行功能统计
	 * 
	 * @param userId
	 * @return
	 */
	List<FunStatsDTO> selectFunStatsByUserId(@Param("userId") String userId, @Param("startTime") String startTime,
                                             @Param("endTim") String endTim);

	/**
	 * 根据参数查询功能的访问者
	 *
	 * @param methodName
	 * @param methodDesc
	 * @param startTime
	 * @param endTim
	 * @return
	 */
	List<FunStatsDTO> selectFeatureVisitor(@Param("methodName") String methodName,
                                           @Param("methodDesc") String methodDesc, @Param("startTime") String startTime,
                                           @Param("endTim") String endTim);
}