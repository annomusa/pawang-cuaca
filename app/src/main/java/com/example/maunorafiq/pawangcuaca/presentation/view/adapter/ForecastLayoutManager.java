package com.example.maunorafiq.pawangcuaca.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by maunorafiq on 12/7/16.
 */

public class ForecastLayoutManager extends LinearLayoutManager{
    /**
     * @param context       Current context, will be used to access resources.
     * @param orientation   Layout orientation. Should be {@link #HORIZONTAL} or {@link
     *                      #VERTICAL}.
     * @param reverseLayout When set to true, layouts from end to start.
     */
    public ForecastLayoutManager(Context context) {
        super(context, HORIZONTAL, false);
    }
}
