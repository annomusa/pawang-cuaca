package com.example.maunorafiq.pawangcuaca.app.base;

import android.content.Context;

/**
 * Created by maunorafiq on 10/28/16.
 */

public interface BasePresenter {
    void onCreate(Context ctx);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}
