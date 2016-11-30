package com.example.maunorafiq.pawangcuaca.presentation.view.fragment;

import android.content.Context;

import com.example.maunorafiq.pawangcuaca.presentation.model.ListWeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.view.CurrentWeatherListView;

import java.util.Collection;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class CurrentCurrentWeatherFragment extends BaseFragment implements CurrentWeatherListView {

    public interface CurrentWeatherListListener {
        void onWeatherClicked(final ListWeatherModel listWeatherModel);
    }



    @Override
    public void renderWeatherList(Collection<ListWeatherModel> listWeatherModels) {

    }

    @Override
    public void viewWeather(ListWeatherModel listWeatherModel) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }
}
