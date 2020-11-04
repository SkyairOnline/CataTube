package com.arudo.catatube.di

import com.arudo.catatube.data.source.CataTubeRepository

object Injection {
    fun provideRepository(): CataTubeRepository {
        return CataTubeRepository.getInstance()
    }
}