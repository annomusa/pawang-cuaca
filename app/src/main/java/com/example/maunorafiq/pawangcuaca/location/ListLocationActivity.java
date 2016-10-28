package com.example.maunorafiq.pawangcuaca.location;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.maunorafiq.pawangcuaca.App;
import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.location.adapter.ItemLocationAdapter;
import com.example.maunorafiq.pawangcuaca.model.City;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.service.RestApi;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class ListLocationActivity extends AppCompatActivity implements ListLocationInterface {
    @Inject RestApi restApi;
    @Bind(R.id.list_location) RecyclerView rvListLocation;

    private String cityReq;
    private ItemLocationAdapter mAdapter;
    private ArrayList<City> cities;
    private ListLocationPresenter mPresenter;
    private String TAG = "List location activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);

        ButterKnife.bind(this);
        App.getApiComponent(this, Constant.oWeatherUrl).inject(this);
        cityReq = "Kebayoran baru";
        mPresenter = new ListLocationPresenter(this);
        cities = new ArrayList<>();

        initToolbarAndFloating();
        configView();
    }

    private void configView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListLocation.setLayoutManager(linearLayoutManager);
        mAdapter = new ItemLocationAdapter(cities);
        rvListLocation.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.fetchWeather();
    }

    @Override
    public void onCompleted() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Log.d(TAG, "onError: " + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(OWeatherResponse response) {
        Log.d(TAG, "onResponse: " + response.getName() + " " + response.getMain().getTemp().toString());
        City city = new City(response.getName(), response.getMain().getTemp().toString());
        cities.add(city);
    }

    @Override
    public Observable<OWeatherResponse> getWeather() {
        //http://api.openweathermap.org/data/2.5/weather?q=kebayoran%20baru&appid=90faa1669716319e787ca1ab5da48cbc
        return restApi.getWeatherByUrl(Constant.oWeatherUrl + "weather?q=" + cityReq + "&appid=" + Constant.oWeatherApi);
    }

    private void initToolbarAndFloating() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
