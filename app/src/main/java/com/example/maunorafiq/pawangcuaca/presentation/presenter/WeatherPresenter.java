package com.example.maunorafiq.pawangcuaca.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.WeatherModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.view.WeatherListView;
import com.icehousecorp.maunorafiq.domain.UseCase;
import com.icehousecorp.maunorafiq.domain.weathers.Weather;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
public class WeatherPresenter implements Presenter {

    private WeatherListView weatherListView;

    private final UseCase getWeatherUseCase;
    private final WeatherModelDataMapper weatherModelDataMapper;

    @Inject
    public WeatherPresenter(@Named("weather") UseCase getWeatherUseCase,
                            WeatherModelDataMapper weatherModelDataMapper) {
        this.getWeatherUseCase = getWeatherUseCase;
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
    }

    private void loadListWeather() {
        this.getListWeather();
    }

    private void getListWeather() {
        this.getWeatherUseCase.execute(new ListWeatherSubscriber());
    }

    private void showListWeatherInView(List<Weather> weathers) {
        final List<WeatherModel> weatherModels =
                this.weatherModelDataMapper.transform(weathers);
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
            WeatherPresenter.this.showListWeatherInView(weathers);
        }
    }
}
