package com.example.maunorafiq.pawangcuaca.presentation.forecast;

import android.support.annotation.NonNull;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.ForecastModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.WeatherModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.base.Presenter;
import com.icehousecorp.maunorafiq.domain.forecast.Forecast;
import com.icehousecorp.maunorafiq.domain.forecast.interactor.GetForecast;
import com.icehousecorp.maunorafiq.domain.weather.Weather;
import com.icehousecorp.maunorafiq.domain.weather.interactor.GetWeather;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by maunorafiq on 11/29/16.
 */
@PerActivity
public class ForecastPresenter implements Presenter {

    private final String TAG = this.getClass().getSimpleName();

    private ForecastView forecastView;

    private final GetForecast getForecastUseCase;
    private final GetWeather getWeatherUseCase;
    private final WeatherModelDataMapper weatherModelDataMapper;
    private final ForecastModelDataMapper forecastModelDataMapper;

    @Inject
    public ForecastPresenter(GetForecast getForecastUseCase,
                             GetWeather getWeatherUseCase,
                             WeatherModelDataMapper weatherModelDataMapper,
                             ForecastModelDataMapper forecastModelDataMapper) {
        this.getForecastUseCase = getForecastUseCase;
        this.getWeatherUseCase = getWeatherUseCase;
        this.weatherModelDataMapper = weatherModelDataMapper;
        this.forecastModelDataMapper = forecastModelDataMapper;
    }

    public void setView(@NonNull ForecastView forecastView) {
        this.forecastView = forecastView;
    }

    @Override
    public void resume() { }

    @Override
    public void pause() { }

    @Override
    public void destroy() {
        getForecastUseCase.unsubscribe();
        getWeatherUseCase.unsubscribe();
        forecastView = null;
    }

    public void initialize(String city) {
        loadForecast(city);
    }

    private void loadForecast(String city) {
        showViewLoading();
        hideViewRetry();
        getForecast(city);
    }

    private void showViewLoading() {
        forecastView.showLoading();
    }

    private void hideViewLoading() {
        forecastView.hideLoading();
    }

    private void showViewRetry() {
        forecastView.showRetry();
    }

    private void hideViewRetry() {
        forecastView.hideRetry();
    }

    private void showViewError(String message) {
        forecastView.showError(message);
    }

    private void showForecastInView(ForecastModel forecastModel) {
        forecastView.renderForecastWeather(forecastModel);
    }

    private void showForecastInView(WeatherModel weatherModel) {
        forecastView.renderForecastWeather(weatherModel);
    }

    private void getForecast(String city) {
        getForecastUseCase.setCity(city);
        getForecastUseCase.execute(new ForecastSubscriber());

        getWeatherUseCase.setCity(city);
        getWeatherUseCase.execute(new WeatherSubscriber());
    }

    private final class ForecastSubscriber extends Subscriber<Forecast> {
        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            showViewError(e.getMessage());
            hideViewLoading();
            showViewRetry();
        }

        @Override
        public void onNext(Forecast forecast) {
            showForecastInView(forecastModelDataMapper.transform(forecast));
        }
    }

    private final class WeatherSubscriber extends Subscriber<Weather> {
        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            showViewError(e.getMessage());
            hideViewLoading();
            showViewRetry();
        }

        @Override
        public void onNext(Weather weather) {
            showForecastInView(weatherModelDataMapper.transform(weather));
        }
    }
}
