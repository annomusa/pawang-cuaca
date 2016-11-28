package com.icehousecorp.maunorafiq.data.current.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.icehousecorp.maunorafiq.data.current.entity.response.CurrentWeatherResponse;
import com.icehousecorp.maunorafiq.domain.current.CurrentWeather;

/**
 * Created by Raffi on 11/25/2016.
 */
@Singleton
public class CurrentWeatherEntityDataMapper {

    @Inject
    public CurrentWeatherEntityDataMapper(){}

    public CurrentWeather transform(CurrentWeatherResponse currentWeatherResponse) {
        CurrentWeather currentWeather = null;
        if (currentWeatherResponse != null) {
            currentWeather = new CurrentWeather(currentWeatherResponse.getId());
            currentWeather.setCityName(currentWeatherResponse.getName());
            currentWeather.setWeatherId(currentWeatherResponse.getWeather().get(0).getId());
            currentWeather.setWeatherName(currentWeatherResponse.getWeather().get(0).getMain());
            currentWeather.setWeatherDescription(currentWeatherResponse.getWeather().get(0).getDescription());
            currentWeather.setWeatherUrlIcon(currentWeatherResponse.getWeather().get(0).getIcon());
        }
        return currentWeather;
    }
}
