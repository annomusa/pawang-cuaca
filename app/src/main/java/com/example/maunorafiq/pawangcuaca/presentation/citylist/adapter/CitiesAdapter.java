package com.example.maunorafiq.pawangcuaca.presentation.citylist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maunorafiq.pawangcuaca.R;
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel;
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by maunorafiq on 11/8/16.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ItemViewHolder> {

    public interface OnItemClickListener {
        void onCityItemClicked(CityModel cityModel);
    }

    private final String TAG = this.getClass().getSimpleName();
    private Context context;
    private List<CityModel> cityModelList;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    @Inject
    public CitiesAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cityModelList = new ArrayList<>();
        cityModelList.add(0, new CityModel());
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return (cityModelList != null) ? cityModelList.size() : 0;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_location, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final CityModel cityModel = cityModelList.get(position);

        if (cityModel.getCityName() != null)
            holder.tvLocation.setText(cityModel.getCityName());
        else
            holder.tvLocation.setText("Your location");

        if (cityModel.getWeatherModel() != null) {
            final WeatherModel weatherModel = cityModel.getWeatherModel();

            holder.tvLastChecked.setText(weatherModel.getWeatherName());
            holder.tvTemperature.setText(weatherModel.getTemperature());
            Picasso.with(context).load(weatherModel.getWeatherIcon()).into(holder.ivTemperature);
        } else {
            holder.tvTemperature.setText("--");
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onCityItemClicked(cityModel);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCityModelList(List<CityModel> cityModelList) {
        boolean isContain = false;
        for (CityModel cityModel : cityModelList) {
            for (int i=1; i<this.cityModelList.size(); i++) {
                if (cityModel.getCityName().equals(this.cityModelList.get(i).getCityName())){
                    isContain = true;
                    break;
                }
            }

            if (!isContain) {
                this.cityModelList.add(cityModel);
            }
        }

        notifyDataSetChanged();
    }

    public void addNewCity(CityModel cityModel) {
        boolean isContain = false;
        for (int i = 1; i<cityModelList.size(); i++){
            if (cityModelList.get(i).getCityName().equals(cityModel.getCityName())){
                isContain = true;
                break;
            }
        }
        if (!isContain){
            cityModelList.add(cityModel);
            notifyDataSetChanged();
        }
    }

    public void updateCityModel(WeatherModel weatherModel) {
        for (int i=1; i<cityModelList.size(); i++){
            if (cityModelList.get(i).getCityName().equals(weatherModel.getCityName())) {
                cityModelList.get(i).setWeatherModel(weatherModel);
                notifyDataSetChanged();
                return;
            }
        }
        cityModelList.get(0).setCityName(weatherModel.getCityName());
        cityModelList.get(0).setWeatherModel(weatherModel);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
