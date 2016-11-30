package com.example.maunorafiq.pawangcuaca.presentation.view;

import android.content.Context;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showRetry();

    void hideRetry();

    void showError(String message);

    Context context();
}
