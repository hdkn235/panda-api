package com.hd.api.model;

import javax.persistence.*;
import java.util.Date;

public class Weather {
    @Id
    @Column(name = "WeatherId")
    private String weatherId;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Place")
    private String place;

    @Column(name = "City")
    private String city;

    @Column(name = "Temp")
    private String temp;

    @Column(name = "Humidity")
    private String humidity;

    @Column(name = "Windy")
    private String windy;

    @Column(name = "Rainy")
    private String rainy;

    @Column(name = "Pm25")
    private String pm25;

    @Column(name = "WbTemp")
    private String wbTemp;

    @Column(name = "WeatherInfo")
    private String weatherInfo;

    public Weather(String weatherId, Date date, String place, String city, String temp, String humidity, String windy, String rainy, String pm25, String wbTemp, String weatherInfo) {
        this.weatherId = weatherId;
        this.date = date;
        this.place = place;
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
        this.windy = windy;
        this.rainy = rainy;
        this.pm25 = pm25;
        this.wbTemp = wbTemp;
        this.weatherInfo = weatherInfo;
    }

    public Weather() {
        super();
    }

    /**
     * @return WeatherId
     */
    public String getWeatherId() {
        return weatherId;
    }

    /**
     * @param weatherId
     */
    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId == null ? null : weatherId.trim();
    }

    /**
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return Place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place
     */
    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    /**
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * @return Temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     * @param temp
     */
    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

    /**
     * @return Humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * @param humidity
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity == null ? null : humidity.trim();
    }

    /**
     * @return Windy
     */
    public String getWindy() {
        return windy;
    }

    /**
     * @param windy
     */
    public void setWindy(String windy) {
        this.windy = windy == null ? null : windy.trim();
    }

    /**
     * @return Rainy
     */
    public String getRainy() {
        return rainy;
    }

    /**
     * @param rainy
     */
    public void setRainy(String rainy) {
        this.rainy = rainy == null ? null : rainy.trim();
    }

    /**
     * @return Pm25
     */
    public String getPm25() {
        return pm25;
    }

    /**
     * @param pm25
     */
    public void setPm25(String pm25) {
        this.pm25 = pm25 == null ? null : pm25.trim();
    }

    /**
     * @return WbTemp
     */
    public String getWbTemp() {
        return wbTemp;
    }

    /**
     * @param wbTemp
     */
    public void setWbTemp(String wbTemp) {
        this.wbTemp = wbTemp == null ? null : wbTemp.trim();
    }

    /**
     * @return WeatherInfo
     */
    public String getWeatherInfo() {
        return weatherInfo;
    }

    /**
     * @param weatherInfo
     */
    public void setWeatherInfo(String weatherInfo) {
        this.weatherInfo = weatherInfo == null ? null : weatherInfo.trim();
    }
}