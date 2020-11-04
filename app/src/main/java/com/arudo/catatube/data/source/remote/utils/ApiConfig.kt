package com.arudo.catatube.data.source.remote.utils

import com.arudo.catatube.BuildConfig
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val client = OkHttpClient
                .Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
            IdleResourcesUtils.registerIdlingResources(client)
            val retrofit = Retrofit
                .Builder()
                .baseUrl(BuildConfig.WEB_API.toHttpUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}