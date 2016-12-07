package com.example.maunorafiq.pawangcuaca.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by maunorafiq on 12/7/16.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ItemViewHolder>{

    private final String TAG = this.getClass().getSimpleName();

    private Context context;
    private List<WeatherModel> weatherModelList;
    private final LayoutInflater layoutInflater;

    @Inject
    public ForecastAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        weatherModelList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_weather_forecast, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final WeatherModel weatherModel = weatherModelList.get(position);
        Log.d(TAG, "onBindViewHolder: " + position);
        holder.tvDayForecast.setText(weatherModel.getDay());
        holder.tvTimeForecast.setText(weatherModel.getHourBegin() + " - " + weatherModel.getHourEnd());
        holder.tvMainWeather.setText(weatherModel.getWeatherName());
        Picasso.with(context).load(weatherModel.getWeatherIcon()).into(holder.ivIconForecast);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return weatherModelList.size();
    }

    public void setWeatherModelList(List<WeatherModel> weatherModelList) {
        this.weatherModelList.addAll(weatherModelList);
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tvDayForecast) TextView tvDayForecast;
        @Bind(R.id.tvTimeForecast) TextView tvTimeForecast;
        @Bind(R.id.tvMainWeather) TextView tvMainWeather;
        @Bind(R.id.ivIconForecast) ImageView ivIconForecast;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
