package com.icehousecorp.maunorafiq.data.forecast.entity.mapper;

import com.icehousecorp.maunorafiq.data.forecast.entity.response.ForecastResponse;
import com.icehousecorp.maunorafiq.data.forecast.entity.response.ListTime;
import com.icehousecorp.maunorafiq.domain.forecast.Forecast;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by maunorafiq on 11/29/16.
 */

@Singleton
public class ForecastEntityDataMapper {

    @Inject
    public ForecastEntityDataMapper() { }

    public Forecast transform(ForecastResponse forecastResponse, String city) {
        Forecast forecast = null;
        if (forecastResponse != null) {
            forecast = new Forecast(forecastResponse.getCity().getId());
            forecast.setCityName(city);

            List<Weather> weatherList = new ArrayList<>();
            for (ListTime item : forecastResponse.getList()) {
                Weather weather = new Weather(forecastResponse.getCity().getId());
                weather.setCityName(forecastResponse.getCity().getName());
                weather.setWeatherId(item.getWeather().get(0).getId());
                weather.setWeatherName(item.getWeather().get(0).getMain());
                weather.setWeatherDescription(item.getWeather().get(0).getDescription());
                weather.setWeatherIcon(item.getWeather().get(0).getIcon());
                weather.setUtcTime(item.getDt());
                weatherList.add(weather);
            }
            forecast.setForecastList(weatherList);
        }
        return forecast;
    }
}
