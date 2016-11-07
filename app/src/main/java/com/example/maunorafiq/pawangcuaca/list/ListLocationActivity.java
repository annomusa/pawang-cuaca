package com.example.maunorafiq.pawangcuaca.list;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.maunorafiq.pawangcuaca.App;
import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.usecase.GetLocation;
import com.example.maunorafiq.pawangcuaca.list.adapter.ItemLocationAdapter;
import com.example.maunorafiq.pawangcuaca.model.reamldb.City;
import com.example.maunorafiq.pawangcuaca.service.RestApi;
import com.example.maunorafiq.pawangcuaca.usecase.GetWeather;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListLocationActivity extends AppCompatActivity implements
        ListLocationContract.View {

    @Inject RestApi restApi;
    @Inject ListLocationPresenter mPresenter;
    @Bind(R.id.list_location) RecyclerView rvListLocation;

    private ItemLocationAdapter mAdapter;
    private ArrayList<City> cities;
    private String TAG = "List location activity";
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 101;

    private String mLastCity;
    private Location mLastLocation;
    private String mLastUpdateTime;

    // Keys for storing activity state in the Bundle.
    protected final static String KEY_CITY = "city";
    protected final static String KEY_LOCATION = "location";
    protected final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);

        ButterKnife.bind(this);

        App.getApiComponent(this, Constant.oWeatherUrl).inject(this);

        mPresenter.setView(this);
        mPresenter.setContext(this);
        cities = new ArrayList<>();
        cities = mPresenter.fetchCities();

        updateValuesFromBundle(savedInstanceState);

        initToolbarAndFloating();
        configRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
        mPresenter.getCurrentLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        Log.d(TAG, "onResume: ");
        for (int i=1;i<cities.size();i++) {
            mPresenter.fetchWeather(i, cities.get(i).getName(), 0, 0);
        }

        mPresenter.printRealmCities();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(KEY_CITY)) {
                mLastCity = savedInstanceState.getString(KEY_CITY);
                cities.get(0).setName(mLastCity);
            }
            if (savedInstanceState.keySet().contains(KEY_LOCATION)) {
                mLastLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            }

            if (savedInstanceState.keySet().contains(KEY_LAST_UPDATED_TIME_STRING)) {
                mLastUpdateTime = savedInstanceState.getString(KEY_LAST_UPDATED_TIME_STRING);
            }
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(KEY_CITY, mLastCity);
        savedInstanceState.putParcelable(KEY_LOCATION, mLastLocation);
        savedInstanceState.putString(KEY_LAST_UPDATED_TIME_STRING, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }



    /*----------- Init view -----------*/
    private void initToolbarAndFloating() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this::openSearchCity);
    }

    private void configRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvListLocation.setLayoutManager(linearLayoutManager);
        mAdapter = new ItemLocationAdapter(cities);
        rvListLocation.setAdapter(mAdapter);
    }
    /*----------- Init view -----------*/



    /*------------ Contract ------------*/
    @Override
    public void showComplete() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Log.d(TAG, "showError: " + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(GetWeather.CityWeather response) {
        if (response.getNumber() == 0){
            cities.get(0).setName(response.getOWeatherResponse().getName());
            cities.get(0).setTemperature(response.getOWeatherResponse().getMain().getTemp().toString());
        } else {
            cities.get(response.getNumber()).setName(response.getCity());
            cities.get(response.getNumber()).setTemperature(response.getOWeatherResponse().getMain().getTemp().toString());
        }
    }

    /*------------ Contract ------------*/



    /*----- Open Search auto complete activity -----*/
    private void openSearchCity(View view){
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
    /*----- Open Search auto complete activity -----*/



    /*----- Handle Permission & Activity Result -----*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PLACE_AUTOCOMPLETE_REQUEST_CODE:
                handlePlaceAutoComplete(resultCode, data);
                break;
            case GetLocation.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mPresenter.requestLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // TODO : Implement handler if user cancel activate GPS
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void handlePlaceAutoComplete(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(this, data);
            Log.i(TAG, "Place: " + place.getId()
                    + " " + place.getName()
                    + " " + place.getLatLng()
                    + " " + place.getAddress()
                    + " " + place.getLocale()
            );
            mPresenter.addNewCity(place.getName().toString());
            mPresenter.addNewRealmCity(place.getId(), place.getName().toString());
            cities = mPresenter.fetchCities();

        }

        else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            Log.i(TAG, status.getStatusMessage());
        }

        else if (resultCode == RESULT_CANCELED) {
            Log.i(TAG, "handlePlaceAutoComplete: Canceled");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case GetLocation.PERMISSION_ACCESS_COURSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPresenter.requestGps();
                }
                else {
                    // TODO : Implement handler if permission not granted
                }
                break;
        }
    }
    /*----- Handle Permission & Activity Result -----*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
