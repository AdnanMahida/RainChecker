package com.ad.composemvvmstarter.network

import com.google.gson.JsonElement
import retrofit2.http.GET

interface DemoService {
    @GET("objects")
    suspend fun getListOfDevices(): JsonElement?
}