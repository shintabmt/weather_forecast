package com.example.weatherforecast.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.ActivityWeatherBinding
import com.example.weatherforecast.ui.adapter.WeatherAdapter
import com.example.weatherforecast.utils.RootUtil
import com.example.weatherforecast.utils.ValueConst.QUERY_LIMIT_LENGTH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityWeatherBinding
    private var adapter: WeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        onInit()

        onObserver()
    }

    private fun onInit() = with(binding) {
        if (RootUtil.isDeviceRooted()) {
            Toast.makeText(
                this@WeatherActivity,
                getString(R.string.device_rooted_error),
                Toast.LENGTH_LONG
            ).show()
            this@WeatherActivity.finish()
        }
        adapter = WeatherAdapter()
        rvWeather.layoutManager = LinearLayoutManager(this@WeatherActivity)
        rvWeather.adapter = adapter

        etSearch.doOnTextChanged { text, _, _, _ ->
            btnSearch.isEnabled = (text?.trim()?.length ?: 0) >= QUERY_LIMIT_LENGTH
        }

        btnSearch.setOnClickListener {
            viewModel.searchWeather(etSearch.text.toString().trim())
            etSearch.setText("")
            adapter?.clear()
        }
    }

    private fun onObserver() {
        viewModel.weathers.observe(this) {
            adapter?.submitList(it)
        }

        viewModel.loading.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, getString(R.string.general_error, it), Toast.LENGTH_LONG).show()
        }
    }
}