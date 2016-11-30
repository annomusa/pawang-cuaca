package com.example.maunorafiq.pawangcuaca.presentation.view;

import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;

import java.util.Collection;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface WeatherListView extends LoadDataView {

    void renderWeatherList(Collection<WeatherModel> weatherModels);

    void viewWeather(WeatherModel weatherModel);
}
