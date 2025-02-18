package com.ad.rainchecker.di

import com.ad.rainchecker.BuildConfig
import com.ad.rainchecker.network.WeatherService
import com.ad.rainchecker.network.interceptors.getHttpInterceptor
import com.ad.rainchecker.utility.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * ApiClientModule used for
 * api related DI
 * */
@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Named("ApiKey")
    fun provideApiKey() = BuildConfig.OPEN_WEATHER_API_KEY

    @Provides
    @Singleton
    fun provideHttpInterceptor() = OkHttpClient.Builder()
        .addInterceptor(getHttpInterceptor())
        .connectTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS).build()


    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("BaseUrl") baseUrl: String, httpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.newBuilder().build()).build()


    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}