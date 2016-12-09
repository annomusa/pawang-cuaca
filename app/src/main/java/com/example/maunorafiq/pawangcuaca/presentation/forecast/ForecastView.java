package com.example.maunorafiq.pawangcuaca.presentation.forecast;

import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.base.LoadDataView;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface ForecastView extends LoadDataView {

    void renderForecastWeather (ForecastModel forecastWeather);

    void renderForecastWeather (WeatherModel weatherModel);
}
