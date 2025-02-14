package com.ad.composemvvmstarter.repository

import com.ad.composemvvmstarter.network.DemoService
import com.ad.composemvvmstarter.network.safeApiCall
import okhttp3.OkHttpClient
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val demoService: DemoService
) {
    fun getHttpInterceptor() = okHttpClient

    fun callDemoBackgroundSyncApi(id: Int) {

    }

    suspend fun callGetListOfDevices(onError: (statusCode: Int, message: String) -> Unit) =
        safeApiCall(onError = onError, callFunction = { demoService.getListOfDevices() })

}