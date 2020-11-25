package com.arudo.catatube.di

import android.content.Context
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.LocalDataSource
import com.arudo.catatube.data.source.local.room.CataTubeDatabase
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.utils.ApiConfig
import kotlinx.coroutines.Dispatchers

object Injection {
    fun provideRepository(context: Context): CataTubeRepository {
        val cataTubeDatabase = CataTubeDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(cataTubeDatabase.cataTubeDao())
        val remoteDataSource = RemoteDataSource(ApiConfig.getApiService())
        val dispatcher = Dispatchers.IO
        return CataTubeRepository.getInstance(localDataSource, remoteDataSource, dispatcher)
    }
}