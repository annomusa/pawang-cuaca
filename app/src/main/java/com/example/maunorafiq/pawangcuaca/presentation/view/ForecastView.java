package com.example.maunorafiq.pawangcuaca.presentation.view;

import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface ForecastView extends LoadDataView {

    void renderForecastWeather (ForecastModel forecastWeather);

    void renderForecastWeather (WeatherModel weatherModel);
}
