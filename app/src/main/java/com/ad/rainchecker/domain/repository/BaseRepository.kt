package com.ad.rainchecker.domain.repository

import com.ad.rainchecker.network.WeatherService
import com.ad.rainchecker.network.safeApiCall
import javax.inject.Inject
import javax.inject.Named

class BaseRepository @Inject constructor(
    @Named("ApiKey") val apiKey: String,
    private val weatherService: WeatherService
) {

    suspend fun getForecastByCity(
        city: String,
        onError: (statusCode: Int, message: String) -> Unit
    ) = safeApiCall(
        onError = onError,
        callFunction = {
            weatherService.getForecastByCity(
                city = city,
                apiKey = apiKey
            )
        })

}