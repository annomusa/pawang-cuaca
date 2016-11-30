package com.example.maunorafiq.pawangcuaca.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.WeatherModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.view.WeatherListView;
import com.icehousecorp.maunorafiq.domain.UseCase;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
public class WeatherPresenter implements Presenter {

    private WeatherListView weatherListView;

    private final UseCase getCurrentWeatherUseCase;
    private final WeatherModelDataMapper weatherModelDataMapper;

    @Inject
    public WeatherPresenter(UseCase getCurrentWeatherUseCase,
                            WeatherModelDataMapper weatherModelDataMapper) {
        this.getCurrentWeatherUseCase = getCurrentWeatherUseCase;
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
        this.getCurrentWeatherUseCase.unsubscribe();
        this.weatherListView = null;
    }

    public void initialize() { }

    private void loadListCurrentWeather() { }

    public void onWeatherClicked(WeatherModel weatherModel) {
        this.weatherListView.viewWeather(weatherModel);
    }



    private final class WeatherSubscriber extends Subscriber {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {

        }
    }
}
