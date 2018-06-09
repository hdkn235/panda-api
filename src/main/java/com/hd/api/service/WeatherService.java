package com.hd.api.service;

import com.hd.api.model.Weather;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WeatherService extends BaseService<Weather> {

	/**
	 * 获取城市最近天气
	 * 
	 * @param city
	 * @return
	 */
	Weather getRecentByCity(String city);

	/**
	 * 获取城市最近天气
	 * 
	 * @param city
	 * @return
	 */
	Weather getRecentByCity(String city, Date date);

	/**
	 * 更新城市天气
	 * 
	 * @param cityList
	 *            城市列表
	 */
	void updateByCity(List<String> cityList);

	/**
	 * 插入天气
	 * 
	 * @param city
	 */
	Weather insertByCity(String city);

	/**
	 * 获取每日的温度曲线数据
	 * 
	 * @param city
	 * @param dateStr
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> listDailyData(String city, String dateStr, List<String> params);

	/**
	 * 获取每日的温度曲线数据
	 * 
	 * @param city
	 * @param dateStr
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> listMontylyData(String city, String dateStr, List<String> params);
}
