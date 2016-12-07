package com.example.maunorafiq.pawangcuaca.presentation.mapper;

import android.util.Log;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;
import com.icehousecorp.maunorafiq.domain.forecast.Forecast;

import javax.inject.Inject;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
public class ForecastModelDataMapper {

    private final String TAG = this.getClass().getSimpleName();

    private final WeatherModelDataMapper weatherModelDataMapper;

    @Inject
    public ForecastModelDataMapper(WeatherModelDataMapper weatherModelDataMapper) {
        this.weatherModelDataMapper = weatherModelDataMapper;
    }

    public ForecastModel transform(Forecast forecast) {
        ForecastModel forecastModel = new ForecastModel(forecast.getCityName());
        forecastModel.setForecastList(weatherModelDataMapper.transform(forecast.getForecastList()));
        return forecastModel;
    }
}
