package com.example.maunorafiq.pawangcuaca.list.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.model.City;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by maunorafiq on 10/28/16.
 */

public class ItemLocationAdapter extends RecyclerView.Adapter<ItemLocationAdapter.ItemViewHolder> {
    private ArrayList<City> cities;

    public ItemLocationAdapter(ArrayList<City> cityList) {
        cities = cityList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_location, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvLocation.setText(cities.get(position).getName());
        holder.tvTemperature.setText(cities.get(position).getTemperature());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.card_view) CardView cardView;
        @Bind(R.id.tv_location) TextView tvLocation;
        @Bind(R.id.tv_location_temperature) TextView tvTemperature;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
