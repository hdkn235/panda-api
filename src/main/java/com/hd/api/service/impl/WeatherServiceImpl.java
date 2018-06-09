package com.hd.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.hd.api.constant.DateTimeConst;
import com.hd.api.dao.WeatherMapper;
import com.hd.api.model.Weather;
import com.hd.api.service.WeatherService;
import com.hd.api.thirdly.WeatherManager;
import com.hd.utils.common.CastUtil;
import com.hd.utils.common.CommonUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WeatherServiceImpl extends BaseServiceImpl<Weather> implements WeatherService {

	@Autowired
	private WeatherMapper wMapper;

	@Autowired
	private WeatherManager wManager;

	@Override
	public Weather getRecentByCity(String city) {
		return wMapper.getLatelyByCity(city);
	}

	@Override
	public Weather getRecentByCity(String city, Date date) {
		return wMapper.getLatelyByCityAndDate(city, date);
	}


	@Override
	public void updateByCity(List<String> cityList) {
		cityList.forEach(city -> {
			insertByCity(city);
		});
	}

	@Override
	public Weather insertByCity(String city) {
		JSONObject weatherObj = wManager.getAPIWeather(city);
		Weather newWeather = null;
		if (weatherObj.length() > 0) {
			newWeather = JSON.parseObject(weatherObj.toString(), Weather.class);
			newWeather.setWeatherId(CommonUtil.createUUID());
			newWeather.setDate(new Date());
			newWeather.setWbTemp(calWbTemp(newWeather.getTemp(), newWeather.getHumidity()));
			wMapper.insertSelective(newWeather);
		}
		return newWeather;
	}

	/**
	 * 计算湿球温度
	 * 
	 * @param temp
	 * @param humidity
	 * @return
	 */
	private String calWbTemp(String temp, String humidity) {
		double t = Double.parseDouble(temp);
		double h = Double.parseDouble(humidity);
		double w = -5.806 + 0.672 * t - 0.006 * t * t + (0.061 + 0.004 * t + 0.000099 * t * t) * h
				+ (-0.000033 - 0.000005 * t - 0.0000001 * t * t) * h * h;
		return CastUtil.toRoundString(w, 0);
	}

	@Override
	public List<Map<String, Object>> listDailyData(String city, String dateStr, List<String> params) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeConst.FORMAT_YEAR_DAY);
		LocalDate startDate = LocalDate.parse(dateStr, formatter);
		LocalDate endDate = startDate.plusDays(1);
		List<Map<String, Object>> weatherDTOs = wMapper.listDailyData(city, startDate.toString(), endDate.toString(),
			params);
		return weatherDTOs;
	}

	@Override
	public List<Map<String, Object>> listMontylyData(String city, String dateStr, List<String> params) {
		String[] dateArr = dateStr.split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), 1);

		LocalDate startDate = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
		LocalDate endDate = LocalDate.of(date.getYear(), date.getMonthValue(), date.lengthOfMonth());
		List<Map<String, Object>> weatherDTOs = wMapper.listMonthlyData(city, startDate.toString(),
			endDate.toString() + DateTimeConst.DAY_LAST, params);
		return weatherDTOs;
	}

}
