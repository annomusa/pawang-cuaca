package com.icehousecorp.maunorafiq.data.forecast.repository.datasource;

import com.google.gson.GsonBuilder;
import com.icehousecorp.maunorafiq.data.forecast.entity.response.ForecastResponse;
import com.icehousecorp.maunorafiq.data.forecast.net.ForecastApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static com.icehousecorp.maunorafiq.data.Constant.API_BASE_URL;
import static com.icehousecorp.maunorafiq.data.Constant.openWeatherApi;

/**
 * Created by maunorafiq on 11/29/16.
 */

public class CloudForecastDataStore implements ForecastDataStore {

    private ForecastApi restApi;

    public CloudForecastDataStore(Retrofit retrofit) {
        this.restApi = retrofit.create(ForecastApi.class);
    }

    @Override
    public Observable<ForecastResponse> getForecastEntity(String city) {
        Map<String, String> data = new HashMap<>();
        data.put("q", city);
        data.put("units", "metric");
        data.put("appid", openWeatherApi);
        return this.restApi.getForecast(data);
    }
}
