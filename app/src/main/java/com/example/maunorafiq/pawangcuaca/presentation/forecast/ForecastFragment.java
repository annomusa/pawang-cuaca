package com.example.maunorafiq.pawangcuaca.presentation.forecast;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseFragment;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.forecast.adapter.ForecastAdapter;
import com.example.maunorafiq.pawangcuaca.presentation.forecast.adapter.ForecastLayoutManager;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class ForecastFragment extends BaseFragment implements ForecastView {

    private final String TAG = this.getClass().getSimpleName();

    @Inject ForecastPresenter forecastPresenter;
    @Inject ForecastAdapter forecastAdapter;

    @Bind(R.id.rl_forecast) RelativeLayout rlForecast;

    @Bind(R.id.tvPressureVal) TextView tvPressureVal;
    @Bind(R.id.tvHumidityVal) TextView tvHumidityVal;
    @Bind(R.id.tvTemperatureVal) TextView tvTemperatureVal;
    @Bind(R.id.ivIcon) ImageView ivWeatherIcon;
    @Bind(R.id.tvDescription) TextView tvWeatherDescription;
    @Bind(R.id.tvLocation) TextView tvLocation;
    @Bind(R.id.rvWeatherForecast) RecyclerView rvForecastList;
    @Bind(R.id.rl_progress) RelativeLayout rlProgress;
    @Bind(R.id.rl_retry) RelativeLayout rlRetry;
    @Bind(R.id.bt_retry) Button btnRetry;

    public ForecastFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(WeatherComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        forecastPresenter.setView(this);
        loadForecast();
    }

    @Override
    public void onResume() {
        super.onResume();
        forecastPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        forecastPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvForecastList.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        forecastPresenter.destroy();
    }

    @Override
    public void renderForecastWeather(ForecastModel forecastModel) {
        if (forecastModel != null) {
            rlForecast.setVisibility(View.VISIBLE);
            forecastAdapter.setWeatherModelList(forecastModel.getForecastList());
        }
    }

    @Override
    public void renderForecastWeather(WeatherModel weatherModel) {
        rlForecast.setVisibility(View.VISIBLE);
        tvPressureVal.setText(weatherModel.getPressure());
        tvHumidityVal.setText(weatherModel.getHumidity());
        tvTemperatureVal.setText(weatherModel.getTemperature());
        Picasso.with(context()).load(weatherModel.getWeatherIcon()).into(ivWeatherIcon);
        tvWeatherDescription.setText(weatherModel.getWeatherDescription());
        tvLocation.setText(weatherModel.getCityName());
    }

    @Override
    public void showLoading() {
        rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rlProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        rlRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        rlProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) { }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void setUpRecyclerView() {
        rvForecastList.setLayoutManager(new ForecastLayoutManager(context()));
        rvForecastList.setAdapter(forecastAdapter);
    }

    private void loadForecast() {
        if (forecastPresenter != null) {
            String city = getArguments().getString("city", "Amsterdam");
            forecastPresenter.initialize(city);
        }
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        loadForecast();
    }
}
