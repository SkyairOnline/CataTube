package com.arudo.catatube.di

import android.content.Context
import com.arudo.catatube.data.source.CataTubeRepository

object Injection {
    fun provideRepository(context: Context): CataTubeRepository {

        //val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return CataTubeRepository.getInstance()
    }
}