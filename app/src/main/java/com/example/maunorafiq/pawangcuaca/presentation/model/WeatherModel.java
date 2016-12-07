package com.example.maunorafiq.pawangcuaca.presentation.model;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class WeatherModel {

    private int weatherId;

    private String weatherName;

    private String weatherDescription;

    private String weatherIcon;

    private String cityName;

    private String temperature;

    private String pressure;

    private String humidity;

    private String day;

    private String hourBegin;

    private String hourEnd;

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHourBegin() {
        return hourBegin;
    }

    public void setHourBegin(String hourBegin) {
        this.hourBegin = hourBegin;
    }

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
        stringBuilder.append("UTC Day : " + this.getDay() + "\n");
        stringBuilder.append("UTC Hour : " + this.getHourBegin() + "-" + this.getHourEnd() + "\n");

        return stringBuilder.toString();
    }
}
