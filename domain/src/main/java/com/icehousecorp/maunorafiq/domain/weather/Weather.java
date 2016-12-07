package com.icehousecorp.maunorafiq.domain.weather;

/**
 * Created by maunorafiq on 11/28/16.
 */

public class Weather {
    private final int cityId;

    public Weather(int cityId) {
        this.cityId = cityId;
    }

    private String cityName;
    private int utcTime;
    private int weatherId;
    private String weatherName;
    private String weatherDescription;
    private String weatherIcon;
    private String temperature;
    private String pressure;
    private String humidity;

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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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

    public int getUtcTime() {
        return utcTime;
    }

    public void setUtcTime(int utcTime) {
        this.utcTime = utcTime;
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

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*** Weather Weather ***\n");
        stringBuilder.append("City id : " + this.getCityId() + "\n");
        stringBuilder.append("City name : " + this.getCityName() + "\n");
        stringBuilder.append("UTC Time : " + this.getUtcTime() + "\n");
        stringBuilder.append("Weather id : " + this.getWeatherId() + "\n");
        stringBuilder.append("Weather name : " + this.getWeatherName() + "\n");
        stringBuilder.append("Weather Description : " + this.getWeatherDescription() + "\n");
        stringBuilder.append("Weather Icon : " + this.getWeatherIcon() + "\n");
        stringBuilder.append("Weather temperature : " + this.getTemperature() + "\n");
        stringBuilder.append("Weather Pressure : " + this.getPressure() + "\n");
        stringBuilder.append("Weather humidity : " + this.getHumidity() + "\n");
        stringBuilder.append("***********************\n");

        return stringBuilder.toString();
    }
}
