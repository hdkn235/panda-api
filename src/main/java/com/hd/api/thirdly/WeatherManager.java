package com.hd.api.thirdly;

import org.json.JSONObject;

public interface WeatherManager {

	/**
	 * 调用网络接口获取天气信息
	 * 
	 * @param city
	 * @return
	 */
	JSONObject getAPIWeather(String city);

}
