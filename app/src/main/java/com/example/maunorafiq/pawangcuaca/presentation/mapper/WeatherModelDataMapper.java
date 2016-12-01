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
        return new WeatherModel();
    }

    public List<WeatherModel> transform(List<Weather> weathers) {
        List<WeatherModel> weatherModels;

        if (weathers != null && !weathers.isEmpty()) {
            weatherModels = new ArrayList<>();
            for (Weather weather : weathers) {
                weatherModels.add(transform(weather));
            }
        } else {
            weatherModels = Collections.emptyList();
        }

        return weatherModels;
    }
}
