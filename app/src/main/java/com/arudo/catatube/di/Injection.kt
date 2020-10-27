package com.arudo.catatube.di

import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.utils.JsonHelper

object Injection {
    fun provideRepository(): CataTubeRepository {
        val remoteDataSource = RemoteDataSource.getRemoteDataSource(JsonHelper())
        return CataTubeRepository.getCataTubeRepository(remoteDataSource)
    }
}