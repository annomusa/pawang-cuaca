package com.example.maunorafiq.pawangcuaca.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.WeatherModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.view.WeatherListView;
import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.GetWeather;
import com.icehousecorp.maunorafiq.domain.weathers.interactor.PutCity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
public class WeatherPresenter implements Presenter {

    private final String TAG = this.getClass().getSimpleName();

    private WeatherListView weatherListView;

    private final GetWeather getWeatherUseCase;
    private final PutCity putCityUseCase;
    private final WeatherModelDataMapper weatherModelDataMapper;

    @Inject
    public WeatherPresenter(GetWeather getWeatherUseCase,
                            PutCity putCityUseCase,
                            WeatherModelDataMapper weatherModelDataMapper) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.putCityUseCase = putCityUseCase;
        this.weatherModelDataMapper = weatherModelDataMapper;
    }

    public void setView(@NonNull WeatherListView weatherListView) {
        this.weatherListView = weatherListView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getWeatherUseCase.unsubscribe();
        this.weatherListView = null;
    }

    public void initialize() {
        this.loadListWeather();
        this.addCity("New York");
    }

    public void addCity(String city) {
        this.putCityUseCase.setCity(city);
        this.putCityUseCase.execute(new WeatherSubscriber());
    }

    private void loadListWeather() {
        this.getListWeather();
    }

    private void getListWeather() {
        this.getWeatherUseCase.setCity("Rotterdam");
        this.getWeatherUseCase.execute(new ListWeatherSubscriber());
    }

    private void showListWeatherInView(List<Weather> weathers) {

    }

    public void onWeatherClicked(WeatherModel weatherModel) {
        this.weatherListView.viewWeather(weatherModel);
    }

    private final class ListWeatherSubscriber extends Subscriber<List<Weather>> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Weather> weathers) {
            for (Weather weather : weathers) {
                Log.d(TAG, "Weathers: " + weather.toString());
            }
        }
    }

    private final class WeatherSubscriber extends Subscriber<Weather> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Weather weather) {
            Log.d(TAG, "Weather: " + weather.toString());
        }
    }
}
