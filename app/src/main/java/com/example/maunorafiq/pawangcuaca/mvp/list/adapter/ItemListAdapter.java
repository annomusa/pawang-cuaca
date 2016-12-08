package com.example.maunorafiq.pawangcuaca.list.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.mvp.Constant;
import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.model.reamldb.RealmCity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by maunorafiq on 11/8/16.
 */

public class ItemListAdapter extends RealmRecyclerViewAdapter<RealmCity, ItemListAdapter.ItemViewHolder> {
    private Context ctx;

    public ItemListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<RealmCity> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
        this.ctx = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_location, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        RealmCity realmCity = getData().get(position);
        holder.tvLocation.setText(realmCity.getName());
        holder.tvTemperature.setText(realmCity.getTemperature() != null ? realmCity.getTemperature() + (char) 0x00B0 : "--");
        holder.tvLastChecked.setText("Last checked : today");
        Picasso.with(ctx).load(Constant.baseUrlImage + realmCity.getImageUrl() + ".png").into(holder.ivTemperature);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_location) TextView tvLocation;
        @Bind(R.id.tv_location_temperature) TextView tvTemperature;
        @Bind(R.id.tv_location_last_checked) TextView tvLastChecked;
        @Bind(R.id.image_weather) ImageView ivTemperature;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
