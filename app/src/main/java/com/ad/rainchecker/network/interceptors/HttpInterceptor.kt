package com.ad.rainchecker.network.interceptors

import okhttp3.logging.HttpLoggingInterceptor


fun getHttpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}