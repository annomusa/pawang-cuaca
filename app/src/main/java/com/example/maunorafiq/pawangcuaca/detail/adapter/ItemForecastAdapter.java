package com.example.maunorafiq.pawangcuaca.detail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.Constant;
import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.model.openweather.forecast.ListTime;
import com.example.maunorafiq.pawangcuaca.utility.CalendarHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by maunorafiq on 11/10/16.
 */

public class ItemForecastAdapter extends RecyclerView.Adapter<ItemForecastAdapter.ItemViewHolder> {

    private static final String TAG = "Item Forecast Adapter";
    private Context context;
    private List<ListTime> listItem;

    public ItemForecastAdapter(Context context, List<ListTime> listItem) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_forecast, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        List<String> dateAndClock = CalendarHelper.getDayAndTime(listItem.get(position).getDtTxt());
        holder.tvDayForecast.setText(dateAndClock.get(0));
        holder.tvTimeForecast.setText(dateAndClock.get(1));
        holder.tvMainWeather.setText(listItem.get(position).getWeather().get(0).getMain());
        Picasso.with(context).load(Constant.baseUrlImage + listItem.get(position).getWeather().get(0).getIcon() + ".png").into(holder.ivIconForecast);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvDayForecast) TextView tvDayForecast;
        @Bind(R.id.tvTimeForecast) TextView tvTimeForecast;
        @Bind(R.id.tvMainWeather) TextView tvMainWeather;
        @Bind(R.id.ivIconForecast) ImageView ivIconForecast;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
