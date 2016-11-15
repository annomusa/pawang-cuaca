package com.example.maunorafiq.pawangcuaca.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.App;
import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.detail.adapter.ItemForecastAdapter;
import com.example.maunorafiq.pawangcuaca.list.decorator.DividerItemDecoration;
import com.example.maunorafiq.pawangcuaca.model.openweather.forecast.ListTime;
import com.example.maunorafiq.pawangcuaca.model.openweather.forecast.OWeatherResponse2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @Inject DetailPresenter mPresenter;
    @Bind(R.id.tvDescription) TextView tvDescription;
    @Bind(R.id.tvLocation) TextView tvLocation;
    @Bind(R.id.ivIcon) ImageView ivIcon;
    @Bind(R.id.tvHumidityVal) TextView tvHumidityVal;
    @Bind(R.id.tvPressureVal) TextView tvPressureVal;
    @Bind(R.id.tvTemperatureVal) TextView tvTemperatureVal;
    @Bind(R.id.tvHumidity) TextView tvHumidity;
    @Bind(R.id.tvPressure) TextView tvPressure;
    @Bind(R.id.tvError) TextView tvError;
    @Bind(R.id.btnReload) Button btnReload;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.rvWeatherForecast) RecyclerView rvWeatherForecast;

    private boolean isLoaded;
    private String mRequest;
    private ItemForecastAdapter mAdapter;
    private List<ListTime> listForecast;
    private static final String TAG = "Detail Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        App.getApiComponent(this, Constant.oWeatherUrl).inject(this);

        mRequest = getIntent().getStringExtra("location");
        isLoaded = false;
        modifyDetail();

        progressBar.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
        btnReload.setVisibility(View.GONE);

        listForecast = new ArrayList<>();

        mPresenter.setView(this);
        mPresenter.requestDetail(mRequest);
        mAdapter = new ItemForecastAdapter(this, listForecast);

        configRecyclerView();
    }

    private void configRecyclerView() {
        rvWeatherForecast.setHasFixedSize(true);
        rvWeatherForecast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvWeatherForecast.setAdapter(mAdapter);
        rvWeatherForecast.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
    }

    void modifyDetail() {
        tvDescription.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        tvLocation.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        ivIcon.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        tvHumidityVal.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        tvPressureVal.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        tvTemperatureVal.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        tvHumidity.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        tvPressure.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        rvWeatherForecast.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
    }

    @OnClick (R.id.btnReload)
    public void reload() {
        isLoaded = false;
        modifyDetail();

        progressBar.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
        btnReload.setVisibility(View.GONE);

        mPresenter.requestDetail(mRequest);
    }

    @Override
    public void showCompletion() {
        isLoaded = true;
        progressBar.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
        btnReload.setVisibility(View.GONE);

        modifyDetail();
    }

    @Override
    public void showError(String message) {
        progressBar.setVisibility(View.GONE);
        isLoaded = false;
        modifyDetail();

        tvError.setVisibility(View.VISIBLE);
        btnReload.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResult(OWeatherResponse2 result) {
        tvLocation.setText(result.getCity().getName());
        tvDescription.setText(result.getList().get(0).getWeather().get(0).getDescription());
        tvHumidityVal.setText(String.valueOf(result.getList().get(0).getMain().getHumidity()) + "%");
        tvPressureVal.setText(String.valueOf(result.getList().get(0).getMain().getPressure()) + " hPa");
        tvTemperatureVal.setText(String.valueOf(result.getList().get(0).getMain().getTemp().intValue()) + (char) 0x00B0 + "C");
        String icon = result.getList().get(0).getWeather().get(0).getIcon();
        Picasso.with(this).load(Constant.baseUrlImage + icon + ".png").into(ivIcon);

        listForecast.clear();
        listForecast.addAll(result.getList());
        Log.d(TAG, "showResult: " + listForecast.size());
        mAdapter.notifyDataSetChanged();
    }
}
