package com.arudo.catatube.di

import android.content.Context
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.LocalDataSource
import com.arudo.catatube.data.source.local.room.CataTubeDatabase
import com.arudo.catatube.data.source.remote.AppExcecutors
import com.arudo.catatube.data.source.remote.utils.ApiConfig

object Injection {
    fun provideRepository(context: Context): CataTubeRepository {
        val appExecutors = AppExcecutors()
        val cataTubeDatabase = CataTubeDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(cataTubeDatabase.cataTubeDao())
        val apiService = ApiConfig.getApiService()
        return CataTubeRepository.getInstance(appExecutors, localDataSource, apiService)
    }
}