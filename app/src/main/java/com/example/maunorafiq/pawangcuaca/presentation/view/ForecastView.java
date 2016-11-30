package com.example.maunorafiq.pawangcuaca.presentation.view;

import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface ForecastView extends LoadDataView {

    void renderForecastWeather (ForecastModel forecastWeather);
}
