package com.icehousecorp.maunorafiq.data.forecast.net;

import com.icehousecorp.maunorafiq.data.forecast.entity.response.ForecastResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by maunorafiq on 11/29/16.
 */

public interface ForecastApi {
    @GET("forecast")
    Observable<ForecastResponse> getForecast(@QueryMap Map<String, String> parameter);
}
