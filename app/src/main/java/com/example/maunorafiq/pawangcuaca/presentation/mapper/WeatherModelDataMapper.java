package com.example.maunorafiq.pawangcuaca.presentation.mapper;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by maunorafiq on 11/29/16.
 */
@PerActivity
public class WeatherModelDataMapper {

    @Inject
    public WeatherModelDataMapper() { }

    public WeatherModel transform(Weather weather) {
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setCityName(weather.getCityName());
        weatherModel.setWeatherId(weather.getWeatherId());
        weatherModel.setWeatherName(weather.getWeatherName());
        weatherModel.setWeatherDescription(weather.getWeatherDescription());
        weatherModel.setWeatherIcon(weather.getWeatherIcon());
        weatherModel.setTemperature(weather.getTemperature());
        weatherModel.setPressure(weather.getPressure());
        weatherModel.setHumidity(weather.getHumidity());

        return weatherModel;
    }

}
