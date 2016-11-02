package com.example.maunorafiq.pawangcuaca.list;

import android.location.Location;

/**
 * Created by maunorafiq on 11/2/16.
 */

public interface GetLocationContract {

    interface View {
        void showLocation(Location location);
    }

    interface UserActionListener {
        void onStartGApi();
        void onResumeGApi();
        void onPauseGApi();
        void onStopGApi();
    }
}
