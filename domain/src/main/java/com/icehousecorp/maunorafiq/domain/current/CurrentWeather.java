package com.icehousecorp.maunorafiq.domain.current;

/**
 * Created by Raffi on 11/28/2016.
 */

public class CurrentWeather {

    private int cityId;

    public CurrentWeather(int cityId) {
        this.cityId = cityId;
    }

    private String cityName;
    private int weatherId;
    private String weatherName;
    private String weatherDescription;
    private String weatherUrlIcon;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getWeatherUrlIcon() {
        return weatherUrlIcon;
    }

    public void setWeatherUrlIcon(String weatherUrlIcon) {
        this.weatherUrlIcon = weatherUrlIcon;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("*** Current Weather ***\n");
        stringBuilder.append("city id =" + this.getCityId() + "\n");
        stringBuilder.append("city name =" + this.getCityName() + "\n");
        stringBuilder.append("weather id =" + this.getWeatherId() + "\n");
        stringBuilder.append("weather name =" + this.getWeatherName() + "\n");
        stringBuilder.append("weather description =" + this.getWeatherDescription() + "\n");
        stringBuilder.append("weather icon Url =" + this.getWeatherUrlIcon() + "\n");
        stringBuilder.append("***********************");

        return stringBuilder.toString();

    }
}
