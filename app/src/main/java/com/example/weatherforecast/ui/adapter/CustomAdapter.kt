package com.example.weatherforecast.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.data.models.Weather
import com.example.weatherforecast.databinding.ItemWeatherBinding
import com.example.weatherforecast.utils.formatDate
import java.util.*

class WeatherAdapter() :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var _data: List<Weather>? = null
    private val dataSet get() = _data ?: emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(items: List<Weather>) {
        _data = emptyList()
        _data = items
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        _data = emptyList()
        notifyDataSetChanged()
    }

    class ViewHolder(private val viewBinding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(weather: Weather) = with(viewBinding) {
            tvDate.text = root.context.getString(R.string.date, weather.date.formatDate())
            tvTemperature.text =
                root.context.getString(R.string.temperature, weather.temperature.toString())
            tvPressure.text = root.context.getString(R.string.pressure, weather.pressure.toString())
            tvHumidity.text = root.context.getString(R.string.humidity, weather.humidity.toString())
            tvDescription.text = root.context.getString(R.string.description, weather.description)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemWeatherBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}