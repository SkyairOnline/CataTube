package com.arudo.catatube.utils

import com.arudo.catatube.data.source.remote.response.MovieListResponse
import com.arudo.catatube.data.source.remote.response.MovieResponse
import com.arudo.catatube.data.source.remote.response.TVListResponse
import com.arudo.catatube.data.source.remote.response.TVResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: String,
        @Query("include_video") includeVideo: String,
        @Query("page") page: String,
    ): Call<MovieListResponse>

    @GET("discover/tv")
    fun getTelevisionList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("page") page: String,
        @Query("timezone") timezone: String,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: String,
    ): Call<TVListResponse>

    @GET("movie/{movieId}")
    fun getMovieData(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Call<MovieResponse>

    @GET("tv/{televisionId}")
    fun getTelevisionData(
        @Path("televisionId") televisionId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Call<TVResponse>
}