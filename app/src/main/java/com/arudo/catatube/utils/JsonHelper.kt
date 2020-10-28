package com.arudo.catatube.utils

import android.content.ContentValues.TAG
import android.util.Log
import com.arudo.catatube.data.source.remote.response.MovieListResponse
import com.arudo.catatube.data.source.remote.response.MovieResponse
import com.arudo.catatube.data.source.remote.response.TVListResponse
import com.arudo.catatube.data.source.remote.response.TVResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {
    private val apiKey = "f895c2153f5a11853f009558d0b0ee2a"

    fun loadListMovies(): MovieListResponse {
        var movieList = MovieListResponse()
        val client = ApiConfig.getApiService().getMovieList(apiKey)
        client.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful) {
                    movieList = response.body()!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return movieList
    }

    fun loadListTelevision(): TVListResponse {
        var televisionList = TVListResponse()
        val client = ApiConfig.getApiService().getTelevisionList(apiKey)
        client.enqueue(object : Callback<TVListResponse> {
            override fun onResponse(
                call: Call<TVListResponse>,
                response: Response<TVListResponse>
            ) {
                if (response.isSuccessful) {
                    televisionList = response.body()!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TVListResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return televisionList
    }

    fun loadMovie(movieId: Int): MovieResponse {
        var movie = MovieResponse()
        val client = ApiConfig.getApiService().getMovieData(apiKey, movieId)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    movie = response.body()!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return movie
    }

    fun loadTelevision(televisionId: Int): TVResponse {
        var television = TVResponse()
        val client = ApiConfig.getApiService().getTelevisionData(apiKey, televisionId)
        client.enqueue(object : Callback<TVResponse> {
            override fun onResponse(
                call: Call<TVResponse>,
                response: Response<TVResponse>
            ) {
                if (response.isSuccessful) {
                    television = response.body()!!
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return television
    }

//    fun loadListMoviesTelevisionData(dataStringKey: String): ArrayList<MovieTVResponse> {
//        urlApi = String()
//        if (dataStringKey == R.string.movie.toString()) {
//            urlApi =
//                "https://api.themoviedb.org/3/discover/movie?api_key=$apiKey&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1"
//        } else if (dataStringKey == R.string.tv_show.toString()) {
//            urlApi =
//                "https://api.themoviedb.org/3/discover/tv?api_key=$apiKey&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&include_null_first_air_dates=false"
//        }
//        val movieTelevisionList = ArrayList<MovieTVResponse>()
//        client.get(urlApi, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(
//                statusCode: Int,
//                headers: Array<out Header>,
//                responseBody: ByteArray
//            ) {
//                try {
//                    val responseObject = JSONObject(responseBody.toString())
//                    val listMovieJSONArray = responseObject.getJSONArray("results")
//                    for (i in 0 until listMovieJSONArray.length()) {
//                        movieTelevisionObjectList = listMovieJSONArray.getJSONObject(i)
//                        movieTelevisionList.add(
//                            MovieTVResponse(
//                                id = movieTelevisionObjectList.getInt("id"),
//                                title = movieTelevisionObjectList.getString("original_title"),
//                                overview = movieTelevisionObjectList.getString("overview"),
//                                image = "https://image.tmdb.org/t/p/w440_and_h660_face${
//                                    movieTelevisionObjectList.getString(
//                                        "poster_path"
//                                    )
//                                }",
//                                type = dataStringKey,
//                            )
//                        )
//                    }
//                } catch (ex: Exception) {
//
//                }
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?,
//                error: Throwable?
//            ) {
//
//            }
//
//        })
//
//        return movieTelevisionList
//    }
//
//    fun loadMovieTelevisionData(
//        movieTelevisionId: String,
//        movieTelevisionType: String
//    ): MovieTVResponse {
//        urlApi = String()
//        if (movieTelevisionType == R.string.movie.toString()) {
//            urlApi =
//                "https://api.themoviedb.org/3/movie/$movieTelevisionId?api_key=$apiKey&language=en-US"
//        } else if (movieTelevisionType == R.string.tv_show.toString()) {
//            urlApi =
//                "https://api.themoviedb.org/3/tv/$movieTelevisionId?api_key=$apiKey&language=en-US"
//        }
//        val movieTelevisionData = MovieTVResponse()
//        client.get(urlApi, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?
//            ) {
//                try {
//                    val responseObject = JSONObject(responseBody.toString())
//                    movieTelevisionData.id = responseObject.getInt("id")
//                    movieTelevisionData.image =
//                        "https://image.tmdb.org/t/p/w440_and_h660_face${responseObject.getString("poster_path")}"
//                    movieTelevisionData.title = responseObject.getString("title")
//                    movieTelevisionData.year = responseObject.getString("release_date")
//                    movieTelevisionData.genre = responseObject.getString("title")
//                    movieTelevisionData.duration = responseObject.getDouble("runtime")
//                    movieTelevisionData.rating = responseObject.getDouble("vote_average")
//                    movieTelevisionData.quote = responseObject.getString("tagline")
//                    movieTelevisionData.overview = responseObject.getString("overview")
//                    movieTelevisionData.status = responseObject.getString("status")
//                    movieTelevisionData.language = responseObject.getString("title")
//                    movieTelevisionData.budget = responseObject.getDouble("budget")
//                    movieTelevisionData.revenue = responseObject.getDouble("revenue")
//                    movieTelevisionData.type = movieTelevisionType
//                } catch (ex: Exception) {
//
//                }
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?,
//                error: Throwable?
//            ) {
//
//            }
//
//        })
//        return movieTelevisionData
//    }
}