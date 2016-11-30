package com.example.maunorafiq.pawangcuaca.presentation.view;

import com.example.maunorafiq.pawangcuaca.presentation.model.DetailWeatherModel;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface ForecastWeatherView extends LoadDataView {

    void renderForecastWeather (DetailWeatherModel forecastWeather);
}
