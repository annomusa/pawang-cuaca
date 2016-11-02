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
import com.example.maunorafiq.pawangcuaca.list.Presenter.GetLocationPresenter;
import com.example.maunorafiq.pawangcuaca.list.Presenter.ListLocationPresenter;
import com.example.maunorafiq.pawangcuaca.list.adapter.ItemLocationAdapter;
import com.example.maunorafiq.pawangcuaca.model.City;
import com.example.maunorafiq.pawangcuaca.model.openweather.OWeatherResponse;
import com.example.maunorafiq.pawangcuaca.service.RestApi;
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
        ListLocationContract.View,
        GetLocationContract.View {

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

    private GetLocationPresenter mInteractor;

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
        mInteractor = new GetLocationPresenter(this);

        cities = new ArrayList<>();
        City city = new City("City", "-");
        cities.add(city);
        updateValuesFromBundle(savedInstanceState);

        initToolbarAndFloating();
        configRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mInteractor.onStartGApi(); // Get Location, Callback on ShowLocation
    }

    @Override
    protected void onResume() {
        super.onResume();
        mInteractor.onResumeGApi();
        mPresenter.onResume();
//        mPresenter.fetchWeatherByCity("Kebayoran Baru");
    }

    @Override
    protected void onPause() {
        mInteractor.onPauseGApi();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mInteractor.onStopGApi();
        super.onStop();
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        Log.d(TAG, "updateValuesFromBundle: checking bundle");
        if(savedInstanceState != null) {
            Log.d(TAG, "updateValuesFromBundle: yey");
            if (savedInstanceState.keySet().contains(KEY_CITY)) {
                mLastCity = savedInstanceState.getString(KEY_CITY);
                Log.d(TAG, "updateValuesFromBundle: " + mLastCity);
                cities.get(0).setName(mLastCity);
            }
            if (savedInstanceState.keySet().contains(KEY_LOCATION)) {
                mLastLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            }

            if (savedInstanceState.keySet().contains(KEY_LAST_UPDATED_TIME_STRING)) {
                mLastUpdateTime = savedInstanceState.getString(KEY_LAST_UPDATED_TIME_STRING);
                Log.d(TAG, "updateValuesFromBundle: " + mLastUpdateTime);
            }
        } else Log.d(TAG, "updateValuesFromBundle: null");
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onSaveInstanceState: saving bundle");
        savedInstanceState.putString(KEY_CITY, mLastCity); Log.d(TAG, "onSaveInstanceState: " + mLastCity);
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
    public void showResult(OWeatherResponse response) {
        Log.d(TAG, "showResult: " + response.getName() + " " + response.getMain().getTemp().toString());
        City city = new City(response.getName(), response.getMain().getTemp().toString());
        mLastCity = response.getName();
        cities.get(0).setName(response.getName());
        cities.get(0).setTemperature(response.getMain().getTemp().toString());
//        cities.add(city);
    }

    @Override
    public void showLocation(Location location) {
        mPresenter.fetchWeatherByCoordinates(location.getLatitude(), location.getLongitude());
//        mPresenter.fetchWeatherByCoordinates(35, 139);
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
            case GetLocationPresenter.REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mInteractor.startLocationUpdate();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.d(TAG, "onActivityResult: gps not activate");
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
                    + " " + place.getLocale());
        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            Log.i(TAG, status.getStatusMessage());
        } else if (resultCode == RESULT_CANCELED) {
            Log.i(TAG, "handlePlaceAutoComplete: Canceled");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case GetLocationPresenter.PERMISSION_ACCESS_COURSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mInteractor.requestActivateGps();
                }
                else {
                    Log.d(TAG, "onRequestPermissionsResult: not granted");
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
