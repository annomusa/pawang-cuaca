package com.example.maunorafiq.pawangcuaca.mvp.usecase.contract;

import android.location.Location;

/**
 * Created by maunorafiq on 11/3/16.
 */

public interface GetLocationContract {
    interface Presenter {

        void retrieveCurrentLocation (Location location);
    }

    interface RequestListener {

        void setPresenter(Presenter presenter);

        void onStartGApi();

        void requestGps();

        void startLocationUpdate();

        void stopGetCurrentLocation();

        void onStopGApi();
    }
}
