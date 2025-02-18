package com.ad.rainchecker.ui.screens.details.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ad.rainchecker.data.model.WeatherInfo

class SharedViewModel : ViewModel() {
    var selectedWeatherData: WeatherInfo? by mutableStateOf(null)
        private set

    fun selectWeather(data: WeatherInfo) {
        selectedWeatherData = data
    }
}