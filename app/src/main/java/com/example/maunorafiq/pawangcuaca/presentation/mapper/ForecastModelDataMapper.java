package com.example.maunorafiq.pawangcuaca.presentation.mapper;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;
import com.icehousecorp.maunorafiq.domain.forecast.Forecast;

import javax.inject.Inject;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
public class ForecastModelDataMapper {

    @Inject
    public ForecastModelDataMapper() {

    }

    public ForecastModel transform(Forecast forecast) {
        ForecastModel forecastModel = new ForecastModel();

        return forecastModel;
    }
}
