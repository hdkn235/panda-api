package com.hd.api.thirdly.impl;

import com.hd.api.thirdly.WeatherManager;
import com.hd.utils.common.CommonUtil;
import com.hd.utils.http.HttpRequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

@Component
public class WeatherManagerImpl implements WeatherManager {

    private static Logger logger = LoggerFactory.getLogger(WeatherManagerImpl.class);

    @Value("#{api.weather_key}")
    private String WEATHER_KEY;

    @Value("#{api.weather_url}")
    private String WEATHER_URL;

    @Value("#{api.weather_icon}")
    private String WEATHER_ICON;

    @Override
    public JSONObject getAPIWeather(String city) {
        String param = null;
        try {
            param = String.format("city=%s&WEATHER_KEY=%s", URLEncoder.encode(city.replace("市", ""), "utf-8"), WEATHER_KEY);
        } catch (Exception e) {
            logger.error(CommonUtil.getStackTraceStr(e));
        }

        String jsonResult = HttpRequestUtil.sendGet(WEATHER_URL, param);
        JSONObject jsonObj = new JSONObject();

        if (StringUtils.isNotEmpty(jsonResult)) {
            logger.info(jsonResult);
            JSONObject weatherObj = new JSONObject(jsonResult).getJSONArray("HeWeather5").getJSONObject(0);
            if (weatherObj.optString("status").equals("ok")) {
                JSONObject now = weatherObj.getJSONObject("now");
                String pm25 = "";
                String qlty = "";
                if (weatherObj.has("aqi")) {
                    JSONObject aqiObj = weatherObj.getJSONObject("aqi").getJSONObject("city");
                    pm25 = aqiObj.getString("pm25");
                    qlty = aqiObj.getString("qlty");
                }
                jsonObj.put("Pm25", pm25);
                // 空气质量
                jsonObj.put("Qlty", qlty);
                jsonObj.put("City", city);
                jsonObj.put("Temp", now.getString("tmp"));
                jsonObj.put("Humidity", now.getString("hum"));
                jsonObj.put("Windy", now.getJSONObject("wind"));
                // 天气情况
                JSONObject cond = now.getJSONObject("cond");
                String code = cond.getString("code");
                String icon = WEATHER_ICON + code + ".png";
                cond.put("icon", icon);
                jsonObj.put("Rainy", cond);
                jsonObj.put("WeatherInfo", weatherObj.toString());
            }
        }

        return jsonObj;
    }

}
