package com.example.maunorafiq.pawangcuaca.presentation.model;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class WeatherModel {

    private String cityName;

    private String weatherIcon;

    private String temperature;

    private String pressure;

    private String humidity;

    private String weatherDescription;

    private int weatherId;

    private String weatherName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** Weather Model ***\n");
        stringBuilder.append("City Name : " + this.getCityName() + "\n");
        stringBuilder.append("Weather Id : " + this.getWeatherId() + "\n");
        stringBuilder.append("Weather Name : " + this.getWeatherName() + "\n");
        stringBuilder.append("Weather Description : " + this.getWeatherDescription() + "\n");
        stringBuilder.append("Weather Icon : " + this.getWeatherIcon() + "\n");
        stringBuilder.append("Temperature : " + this.getTemperature() + "\n");
        stringBuilder.append("Pressure : " + this.getPressure() + "\n");
        stringBuilder.append("Humidity : " + this.getHumidity() + "\n");

        return stringBuilder.toString();
    }
}
