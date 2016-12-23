package com.example.maunorafiq.pawangcuaca.presentation.model;

/**
 * Created by maunorafiq on 12/5/16.
 */

public class CityModel {

    private String cityName;

    public CityModel() {

    }

    public CityModel(String cityName) {
        this.cityName = cityName;
    }

    private int ordinal;
    private WeatherModel weatherModel;

    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    public void setWeatherModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** City Model ***\n");
        stringBuilder.append("City name : " + this.getCityName() + "\n");
        stringBuilder.append("City Ordinal : " + this.getOrdinal() + "\n");
        stringBuilder.append("Weather Icon : " + this.getWeatherModel().toString() + "\n");
        stringBuilder.append("******************\n");

        return stringBuilder.toString();
    }
}
