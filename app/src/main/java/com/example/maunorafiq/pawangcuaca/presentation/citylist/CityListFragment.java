package com.example.maunorafiq.pawangcuaca.presentation.citylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseFragment;
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent;
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.example.maunorafiq.pawangcuaca.presentation.citylist.adapter.CitiesAdapter;
import com.example.maunorafiq.pawangcuaca.presentation.citylist.adapter.CitiesLayoutManager;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.google.android.gms.location.places.ui.PlaceAutocomplete.RESULT_ERROR;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class CityListFragment extends BaseFragment implements CityListView {

    private final String TAG = getClass().getSimpleName();

    public interface CityListListener {
        void onCityClicked(final CityModel cityModel);
    }

    @Inject CityListPresenter cityListPresenter;
    @Inject CitiesAdapter citiesAdapter;

    @Bind(R.id.rv_city_list) RecyclerView rvCityList;
    @Bind(R.id.srl_content_list_location) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.rl_progress) RelativeLayout rlProgress;
    @Bind(R.id.fab_add_city) FloatingActionButton fabAddCity;

    private CityListListener cityListListener;
    private final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 123;

    public CityListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CityListListener) {
            cityListListener = (CityListListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(WeatherComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.activity_list_location, container, false);
        ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();
        setUpRefreshLayout();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cityListPresenter.setView(this);
        loadCityList();
    }

    @Override
    public void onResume() {
        super.onResume();
        cityListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        cityListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rvCityList.setAdapter(null);
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cityListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cityListListener = null;
    }

    @Override
    public void showLoading() {
        rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rlProgress.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {
        Toast.makeText(context(), "Error Occur\nSwipe Down To Reload!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideRetry() { }

    @Override
    public void renderCityList(List<CityModel> cityModelList) {
        if (cityModelList != null) {
            citiesAdapter.setCityModelList(cityModelList);
        }
    }

    @Override
    public void viewCity(CityModel cityModel) {
        if (cityModel != null) {
            cityListListener.onCityClicked(cityModel);
        }
    }

    @Override
    public void updateCity(WeatherModel weatherModel) {
        citiesAdapter.updateCityModel(weatherModel);
    }

    @Override
    public void addNewCity(CityModel cityModel) {
        citiesAdapter.addNewCity(cityModel);
    }

    @Override
    public void showError(String message) {
        Log.d(TAG, "showError: " + message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void setUpRecyclerView() {
        citiesAdapter.setOnItemClickListener(onItemClickListener);
        rvCityList.setLayoutManager(new CitiesLayoutManager(context()));
        rvCityList.setAdapter(citiesAdapter);
    }

    private void setUpRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this::loadCityList);
    }

    private void loadCityList() {
        cityListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        loadCityList();
    }

    private CitiesAdapter.OnItemClickListener onItemClickListener = cityModel -> {
        if (cityListPresenter != null && cityModel!= null) {
            cityListPresenter.onCityClicked(cityModel);
        }
    };

    @OnClick(R.id.fab_add_city)
    void setOnFabClick() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(getActivity());
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PLACE_AUTOCOMPLETE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(context(), data);
                    cityListPresenter.addCity(place.getName().toString());
                } else if (resultCode == RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(context(), data);
                    Log.d(TAG, "onActivityResult: " + status.toString());
                } else if (resultCode == RESULT_CANCELED) {
                    Status status = PlaceAutocomplete.getStatus(context(), data);
                    Log.d(TAG, "onActivityResult: " + status.toString());
                }
                break;
        }
    }
}
