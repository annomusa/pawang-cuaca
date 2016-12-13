package com.example.maunorafiq.pawangcuaca.presentation.forecast.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.maunorafiq.pawangcuaca.R
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel
import com.squareup.picasso.Picasso

import java.util.ArrayList

import javax.inject.Inject

/**
 * Created by maunorafiq on 12/7/16.
 */

class ForecastAdapter
@Inject
constructor(private val context: Context) : RecyclerView.Adapter<ForecastAdapter.ItemViewHolder>() {

    private val TAG = this.javaClass.simpleName

    private val weatherModelList: MutableList<WeatherModel>
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        weatherModelList = ArrayList<WeatherModel>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_weather_forecast, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val weatherModel = weatherModelList[position]
        holder.tvDayForecast.text = weatherModel.day

        val time = weatherModel.hourBegin + " - " + weatherModel.hourEnd
        holder.tvTimeForecast.text = time
        holder.tvMainWeather.text = weatherModel.weatherName
        Picasso.with(context).load(weatherModel.weatherIcon).into(holder.ivIconForecast)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return weatherModelList.size
    }

    fun setWeatherModelList(weatherModelList: List<WeatherModel>) {
        this.weatherModelList.addAll(weatherModelList)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tvDayForecast: TextView
        internal var tvTimeForecast: TextView
        internal var tvMainWeather: TextView
        internal var ivIconForecast: ImageView

        init {
            tvDayForecast = itemView.findViewById(R.id.tvDayForecast) as TextView
            tvTimeForecast = itemView.findViewById(R.id.tvTimeForecast) as TextView
            tvMainWeather = itemView.findViewById(R.id.tvMainWeather) as TextView
            ivIconForecast = itemView.findViewById(R.id.ivIconForecast) as ImageView
        }
    }
}
