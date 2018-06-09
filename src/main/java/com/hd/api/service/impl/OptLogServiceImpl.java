package com.hd.api.service.impl;

import com.hd.api.constant.DateTimeConst;
import com.hd.api.dao.OptLogMapper;
import com.hd.api.dto.FunStatsDTO;
import com.hd.api.model.OptLog;
import com.hd.api.service.OptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OptLogServiceImpl extends BaseServiceImpl<OptLog> implements OptLogService {

	@Autowired
	private OptLogMapper olMapper;

	@Override
	public List<FunStatsDTO> selectFunStatsByUserId(String userId, String methodDesc, String beginTime) {
		return olMapper.selectFunStatsByUserId(userId, methodDesc, beginTime);
	}

	@Override
	public List<FunStatsDTO> selectFeatureVisitor(String methodName, String methodDesc, String beginTime,
                                                  String endTime) {
		String endTim = null;
		if (beginTime != null & beginTime != "" || endTime != null & beginTime != "") {
			DateTimeFormatter format = DateTimeFormatter.ofPattern(DateTimeConst.FORMAT_YEAR_DAY);
			LocalDate endLocalDate = LocalDate.parse(endTime, format).plusDays(1);
			endTim = format.format(endLocalDate);
		}
		return olMapper.selectFeatureVisitor(methodName, methodDesc, beginTime, endTim);
	}

}
