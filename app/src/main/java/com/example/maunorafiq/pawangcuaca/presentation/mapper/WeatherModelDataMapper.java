package com.example.maunorafiq.pawangcuaca.presentation.mapper;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.icehousecorp.maunorafiq.domain.weather.Weather;

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

        return weatherModel;
    }
}
