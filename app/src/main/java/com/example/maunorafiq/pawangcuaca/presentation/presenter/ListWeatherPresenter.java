package com.example.maunorafiq.pawangcuaca.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.maunorafiq.pawangcuaca.presentation.internal.di.PerActivity;
import com.example.maunorafiq.pawangcuaca.presentation.mapper.CurrentWeatherModelDataMapper;
import com.example.maunorafiq.pawangcuaca.presentation.model.ListWeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.view.CurrentWeatherListView;
import com.icehousecorp.maunorafiq.domain.UseCase;

import javax.inject.Inject;

/**
 * Created by maunorafiq on 11/29/16.
 */

@PerActivity
public class ListWeatherPresenter implements Presenter {

    private CurrentWeatherListView currentWeatherListView;

    private final UseCase getCurrentWeatherUseCase;
    private final CurrentWeatherModelDataMapper currentWeatherModelDataMapper;

    @Inject
    public ListWeatherPresenter(UseCase getCurrentWeatherUseCase,
                                CurrentWeatherModelDataMapper currentWeatherModelDataMapper) {
        this.getCurrentWeatherUseCase = getCurrentWeatherUseCase;
        this.currentWeatherModelDataMapper = currentWeatherModelDataMapper;
    }

    public void setView(@NonNull CurrentWeatherListView currentWeatherListView) {
        this.currentWeatherListView = currentWeatherListView;
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
        this.currentWeatherListView = null;
    }

    public void initialize() { }

    private void loadListCurrentWeather() { }

    public void onWeatherClicked(ListWeatherModel listWeatherModel) {
        this.currentWeatherListView.viewWeather(listWeatherModel);
    }
}
