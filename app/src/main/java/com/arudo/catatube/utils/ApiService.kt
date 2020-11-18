package com.arudo.catatube.utils

import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.data.source.local.entity.MovieListEntity
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.data.source.local.entity.TVListEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: String,
        @Query("include_video") includeVideo: String,
        @Query("page") page: String,
    ): Response<MovieListEntity>

    @GET("discover/tv")
    suspend fun getTelevisionList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: String,
        @Query("timezone") timezone: String,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: String,
    ): Response<TVListEntity>

    @GET("movie/{movieId}")
    suspend fun getMovieData(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Response<MovieEntity>

    @GET("tv/{televisionId}")
    suspend fun getTelevisionData(
        @Path("televisionId") televisionId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Response<TVEntity>
}