package com.example.maunorafiq.pawangcuaca.presentation.model;

import java.util.List;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class ForecastModel {

    private final String cityName;

    public ForecastModel(String cityName) {
        this.cityName = cityName;
    }

    private WeatherModel currentWeather;
    private List<WeatherModel> forecastList;

    public String getCityName() {
        return cityName;
    }

    public WeatherModel getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherModel currentWeather) {
        this.currentWeather = currentWeather;
    }

    public List<WeatherModel> getForecastList() {
        return forecastList;
    }

    public void setForecastList(List<WeatherModel> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** Forecast ***");
        stringBuilder.append("City name : " + getCityName());
        stringBuilder.append("Weather : " + getCurrentWeather().toString());
        stringBuilder.append("Forecast : " + getForecastList().size());

        return stringBuilder.toString();
    }
}
