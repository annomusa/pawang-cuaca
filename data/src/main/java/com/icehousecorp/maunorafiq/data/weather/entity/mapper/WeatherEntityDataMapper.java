package com.icehousecorp.maunorafiq.data.weather.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.icehousecorp.maunorafiq.data.weather.entity.response.WeatherResponse;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class WeatherEntityDataMapper {

    @Inject
    public WeatherEntityDataMapper(){}

    public Weather transform(WeatherResponse weatherResponse, String city) {
        Weather weather = null;
        if (weatherResponse != null) {
            weather = new Weather(weatherResponse.getId());
            weather.setCityName(city);
            weather.setWeatherId(weatherResponse.getWeather().get(0).getId());
            weather.setWeatherName(weatherResponse.getWeather().get(0).getMain());
            weather.setWeatherDescription(weatherResponse.getWeather().get(0).getDescription());
            weather.setWeatherIcon(weatherResponse.getWeather().get(0).getIcon());
            weather.setUtcTime(weatherResponse.getDt());
        }
        return weather;
    }
}
