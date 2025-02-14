package com.ad.composemvvmstarter.network.interceptors

import com.ad.composemvvmstarter.preference.AppPreference
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthorizationInterceptor(private val preferences: AppPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = preferences.accessToken
        var strToken = ""
        if (accessToken == "") {
            strToken
        } else {
            strToken = "Bearer $accessToken"
        }
        val request = chain.request().newBuilder().addHeader(
            "Authorization", strToken
        ).build()

        Timber.tag("Interceptor").d("intercept: $strToken \naccessToken: $accessToken")
        return chain.proceed(request)
    }
}