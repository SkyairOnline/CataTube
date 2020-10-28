package com.arudo.catatube.utils

import com.arudo.catatube.data.source.remote.response.MovieListResponse
import com.arudo.catatube.data.source.remote.response.MovieResponse
import com.arudo.catatube.data.source.remote.response.TVListResponse
import com.arudo.catatube.data.source.remote.response.TVResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key={apiKey}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    fun getMovieList(
        @Path("apiKey") apiKey: String
    ): Call<MovieListResponse>

    @GET("discover/tv?api_key={apiKey}&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&include_null_first_air_dates=false")
    fun getTelevisionList(
        @Path("apiKey") apiKey: String
    ): Call<TVListResponse>

    @GET("movie/{movieId}?api_key={apiKey}&language=en-US")
    fun getMovieData(
        @Path("apiKey") apiKey: String,
        @Path("movieId") movieId: Int,
    ): Call<MovieResponse>

    @GET("tv/{televisionId}?api_key={apiKey}&language=en-US")
    fun getTelevisionData(
        @Path("apiKey") apiKey: String,
        @Path("televisionId") televisionId: Int,
    ): Call<TVResponse>
}