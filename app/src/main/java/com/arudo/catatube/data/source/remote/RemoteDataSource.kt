package com.arudo.catatube.data.source.remote

import com.arudo.catatube.BuildConfig
import com.arudo.catatube.utils.ApiService

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {
    private val apiKey = BuildConfig.API_KEY

    suspend fun getMovieList() = getResult {
        apiService.getMovieList(apiKey, "en-US", "popularity.desc", "false", "false", "1")
    }

    suspend fun getTelevisionList() = getResult {
        apiService.getTelevisionList(
            apiKey,
            "en-US",
            "popularity.desc",
            "1",
            "America%2FNew_York",
            "false"
        )
    }

    suspend fun getMovieData(movieId: Int) = getResult {
        apiService.getMovieData(movieId, apiKey, "en-US")
    }

    suspend fun getTelevisionData(televisionId: Int) = getResult {
        apiService.getTelevisionData(televisionId, apiKey, "en-US")
    }
}