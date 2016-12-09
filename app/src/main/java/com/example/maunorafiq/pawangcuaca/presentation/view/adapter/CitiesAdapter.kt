package com.example.maunorafiq.pawangcuaca.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.maunorafiq.pawangcuaca.R
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel
import com.squareup.picasso.Picasso

import java.util.ArrayList

import javax.inject.Inject

/**
 * Created by maunorafiq on 11/8/16.
 */

class CitiesAdapter
@Inject
constructor(private val context: Context) : RecyclerView.Adapter<CitiesAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onCityItemClicked(cityModel: CityModel)
    }

    private val cityModelList: MutableList<CityModel>?
    private val layoutInflater: LayoutInflater

    private var onItemClickListener: OnItemClickListener? = null

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        cityModelList = ArrayList<CityModel>()
    }

    override fun getItemCount(): Int {
        return cityModelList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_list_location, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cityModel = cityModelList!![position]

        holder.tvLocation.text = cityModel.cityName
        if (cityModel.weatherModel != null) {
            val weatherModel = cityModel.weatherModel

            holder.tvLastChecked.text = weatherModel?.weatherName
            holder.tvTemperature.text = weatherModel?.temperature
            Picasso.with(context).load(weatherModel?.weatherIcon).into(holder.ivTemperature)
        } else {
            holder.tvTemperature.text = "--"
        }

        holder.itemView.setOnClickListener { v ->
            if (onItemClickListener != null) {
                onItemClickListener!!.onCityItemClicked(cityModel)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setCityModelList(cityModelList: List<CityModel>) {
        var isContain = false
        for (cityModel in cityModelList) {
            for (cityModel1 in this.cityModelList!!) {
                if (cityModel.cityName == cityModel1.cityName) {
                    isContain = true
                    break
                }
            }

            if (!isContain) {
                this.cityModelList.add(cityModel)
            }
        }

        notifyDataSetChanged()
    }

    fun addNewCity(cityModel: CityModel) {
        val isContain = cityModelList!!.any { it.cityName == cityModel.cityName }
        if (!isContain) {
            cityModelList.add(cityModel)
            notifyDataSetChanged()
        }
    }

    fun updateCityModel(weatherModel: WeatherModel) {
        for (cityModel in cityModelList!!) {
            if (cityModel.cityName == weatherModel.cityName) {
                cityModel.weatherModel = weatherModel
            }
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvLocation: TextView
        internal var tvTemperature: TextView
        internal var tvLastChecked: TextView
        internal var ivTemperature: ImageView

        init {
            tvLocation = itemView.findViewById(R.id.tv_location) as TextView
            tvTemperature = itemView.findViewById(R.id.tv_location_temperature) as TextView
            tvLastChecked = itemView.findViewById(R.id.tv_location_last_checked) as TextView
            ivTemperature = itemView.findViewById(R.id.image_weather) as ImageView
        }
    }
}
