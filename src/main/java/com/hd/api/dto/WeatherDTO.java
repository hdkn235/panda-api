package com.hd.api.dto;

/**
 * 天气相关
 * 
 * @author hoda
 *
 */
public class WeatherDTO {

	private String Date;
	private Double Temp;
	private Double Humidity;

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Double getTemp() {
		return Temp;
	}

	public void setTemp(Double temp) {
		Temp = temp;
	}

	public Double getHumidity() {
		return Humidity;
	}

	public void setHumidity(Double humidity) {
		Humidity = humidity;
	}

}
