package com.hd.api.dao;

import com.hd.api.baseDao.BaseMapper;
import com.hd.api.model.Weather;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WeatherMapper extends BaseMapper<Weather> {

	/**
	 * 获取最近一小时天气
	 * 
	 * @param city
	 * @return
	 */
	Weather getLatelyByCity(String city);

	/**
	 * 获取指定时间最近一小时天气
	 * 
	 * @param city
	 * @return
	 */
	Weather getLatelyByCityAndDate(@Param("city") String city, @Param("date") Date date);

	/**
	 * 统计一月天气信息
	 * 
	 * @param city
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Map<String, Object>> listMonthlyData(@Param("city") String city, @Param("startDate") String startDate,
                                              @Param("endDate") String endDate, @Param("params") List<String> params);

	/**
	 * 统计一月天气信息
	 *
	 * @param city
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Map<String, Object>> listDailyData(@Param("city") String city, @Param("startDate") String startDate,
                                            @Param("endDate") String endDate, @Param("params") List<String> params);
}