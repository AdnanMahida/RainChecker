package com.ad.composemvvmstarter.di

import android.content.Context
import com.ad.composemvvmstarter.BuildConfig
import com.ad.composemvvmstarter.network.DemoService
import com.ad.composemvvmstarter.network.interceptors.AuthorizationInterceptor
import com.ad.composemvvmstarter.preference.AppPreference
import com.ad.composemvvmstarter.utility.AppConstants
import com.ad.composemvvmstarter.network.interceptors.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * ApiClientModule used for
 * api related DI
 * */
@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {
    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    fun provideAppPreference(
        @ApplicationContext context: Context
    ) = AppPreference(context = context)

    @Provides
    @Singleton
    fun provideHttpInterceptor(
        preferences: AppPreference
    ) = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor(preferences))
        .addInterceptor(CacheInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).connectTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS).build()


    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String, httpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.newBuilder().build()).build()


    @Provides
    @Singleton
    fun provideDemoService(retrofit: Retrofit): DemoService {
        return retrofit.create(DemoService::class.java)
    }
}