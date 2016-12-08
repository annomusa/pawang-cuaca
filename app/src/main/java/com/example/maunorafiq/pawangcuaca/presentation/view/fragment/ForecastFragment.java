package com.example.maunorafiq.pawangcuaca.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.model.ForecastModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.presenter.ForecastPresenter;
import com.example.maunorafiq.pawangcuaca.presentation.view.ForecastView;
import com.example.maunorafiq.pawangcuaca.presentation.view.adapter.ForecastAdapter;
import com.example.maunorafiq.pawangcuaca.presentation.view.adapter.ForecastLayoutManager;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class ForecastFragment extends BaseFragment implements ForecastView {

    private final String TAG = this.getClass().getSimpleName();

    @Inject ForecastPresenter forecastPresenter;
    @Inject ForecastAdapter forecastAdapter;

    RelativeLayout rlForecast;
    TextView tvPressureVal;
    TextView tvHumidityVal;
    TextView tvTemperatureVal;
    ImageView ivWeatherIcon;
    TextView tvWeatherDescription;
    TextView tvLocation;
    RecyclerView rvForecastList;
    RelativeLayout rlProgress;
    RelativeLayout rlRetry;
    Button btnRetry;

    public ForecastFragment() {
        setRetainInstance(true);
    }

    private void findViewById(View fragmentView) {
        rlForecast = (RelativeLayout) fragmentView.findViewById(R.id.rl_forecast);
        tvPressureVal = (TextView) fragmentView.findViewById(R.id.tvPressureVal);
        tvHumidityVal = (TextView) fragmentView.findViewById(R.id.tvHumidityVal);
        tvTemperatureVal = (TextView) fragmentView.findViewById(R.id.tvTemperatureVal);
        ivWeatherIcon = (ImageView) fragmentView.findViewById(R.id.ivIcon);
        tvWeatherDescription = (TextView) fragmentView.findViewById(R.id.tvDescription);
        tvLocation = (TextView) fragmentView.findViewById(R.id.tvLocation);
        rvForecastList = (RecyclerView) fragmentView.findViewById(R.id.rvWeatherForecast);
        rlProgress = (RelativeLayout) fragmentView.findViewById(R.id.rl_progress);
        rlRetry = (RelativeLayout) fragmentView.findViewById(R.id.rl_retry);
        btnRetry = (Button) fragmentView.findViewById(R.id.bt_retry);
        btnRetry.setOnClickListener(v -> loadForecast());
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
        findViewById(fragmentView);
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
}
