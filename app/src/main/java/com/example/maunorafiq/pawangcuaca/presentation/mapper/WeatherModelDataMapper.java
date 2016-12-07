package com.example.maunorafiq.pawangcuaca.presentation.mapper;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.icehousecorp.maunorafiq.domain.weather.Weather;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
        weatherModel.setCityName(weather.getCityName());
        weatherModel.setWeatherId(weather.getWeatherId());
        weatherModel.setWeatherName(weather.getWeatherName());
        weatherModel.setWeatherDescription(weather.getWeatherDescription());
        weatherModel.setWeatherIcon(weather.getWeatherIcon());
        weatherModel.setTemperature(weather.getTemperature().concat(String.valueOf((char) 0x00B0)).concat("C"));
        weatherModel.setPressure(weather.getPressure().concat(" hPa"));
        weatherModel.setHumidity(weather.getHumidity().concat("%"));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTimeInMillis(weather.getUtcTime() * 1000);

        int hour = Integer.valueOf(new SimpleDateFormat("HH", Locale.ENGLISH).format(calendar.getTime()));
        String hourBegin = String.valueOf(hour - 2);
        String hourEnd = String.valueOf(Integer.valueOf(hourBegin) + 3);

        weatherModel.setDay(new SimpleDateFormat("EEE", Locale.ENGLISH).format(calendar.getTime()));
        weatherModel.setHourBegin(hourBegin);
        weatherModel.setHourEnd(hourEnd);

        return weatherModel;
    }

    public List<WeatherModel> transform(List<Weather> weathers) {
        List<WeatherModel> result;

        if (weathers != null) {
            result = new ArrayList<>();
            for (Weather weather : weathers) {
                result.add(transform(weather));
            }
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

}
