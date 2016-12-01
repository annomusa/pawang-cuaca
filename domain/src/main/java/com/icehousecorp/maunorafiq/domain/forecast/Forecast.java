package com.icehousecorp.maunorafiq.domain.forecast;

import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.List;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class Forecast {
    private final int cityId;

    public Forecast(int cityId) {
        this.cityId = cityId;
    }

    private String cityName;
    private Weather currentWeather;
    private List<Weather> forecastList;

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Weather> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<Weather> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** Forecast Weather ***");
        stringBuilder.append("City id : " + this.getCityId());
        stringBuilder.append("City name : " + this.getCityName());
//        stringBuilder.append("Current Weather : " + this.getCurrentWeather().getCityName());
        stringBuilder.append("Forecast Weather : " + this.getForecastList().size());

        return stringBuilder.toString();
    }
}
