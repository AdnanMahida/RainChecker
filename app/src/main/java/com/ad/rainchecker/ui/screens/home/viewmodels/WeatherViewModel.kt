package com.ad.rainchecker.ui.screens.home.viewmodels

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.ad.rainchecker.core.BaseViewModel
import com.ad.rainchecker.core.Resource
import com.ad.rainchecker.data.model.WeatherInfo
import com.ad.rainchecker.domain.location.LocationTracker
import com.ad.rainchecker.domain.repository.BaseRepository
import com.ad.rainchecker.utility.helper.NetworkGeneric
import com.ad.rainchecker.utility.helper.getCityNameFromLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    baseRepository: BaseRepository,
    private val locationTracker: LocationTracker
) : BaseViewModel(baseRepository) {

    private val _weatherFlow =
        MutableStateFlow<Resource<WeatherInfo?>?>(value = null)
    val weatherFlow: StateFlow<Resource<WeatherInfo?>?> = _weatherFlow

    fun callGetWeather(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherFlow.value = Resource.Loading()

            locationTracker.getCurrentLocation()?.let { location ->
                Timber.e("Location found ${location.longitude} ${location.longitude}")
                val city = getCityNameFromLocation(location = location, context = context)
                if (city.isNullOrEmpty()) {
                    _weatherFlow.value = Resource.Error(500, "City Name not found")
                } else {
                    val response =
                        baseRepository.getForecastByCity(city = city) { error, message ->
                            _weatherFlow.value = Resource.Error(error, message)
                        } ?: return@launch

                    val listItem =
                        NetworkGeneric(response).getAsObject(WeatherInfo::class.java)
                    _weatherFlow.value = Resource.Success(listItem)
                }

            } ?: kotlin.run {
                _weatherFlow.value = Resource.Error(500, "Location not found")
                Timber.e("Error")
            }
        }
    }
}