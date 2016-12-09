package com.example.maunorafiq.pawangcuaca.presentation.view

import android.content.Context

/**
 * Created by maunorafiq on 11/29/16.
 */

interface LoadDataView {

    fun showLoading()

    fun hideLoading()

    fun showRetry()

    fun hideRetry()

    fun showError(message: String?)

    fun context(): Context
}
