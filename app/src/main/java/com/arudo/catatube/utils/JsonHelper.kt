package com.arudo.catatube.utils

import com.arudo.catatube.R
import com.arudo.catatube.data.source.remote.response.MovieTVResponse
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class JsonHelper {
    private val apiKey = "f895c2153f5a11853f009558d0b0ee2a"
    private val client = AsyncHttpClient()
    private lateinit var urlApi: String
    private var movieTelevisionObjectList = JSONObject()

    fun loadListMoviesTelevisionData(dataStringKey: String): ArrayList<MovieTVResponse> {
        urlApi = String()
        if (dataStringKey == R.string.movie.toString()) {
            urlApi =
                "https://api.themoviedb.org/3/discover/movie?api_key=$apiKey&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1"
        } else if (dataStringKey == R.string.tv_show.toString()) {
            urlApi =
                "https://api.themoviedb.org/3/discover/tv?api_key=$apiKey&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&include_null_first_air_dates=false"
        }
        val movieTelevisionList = ArrayList<MovieTVResponse>()
        client.get(urlApi, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val responseObject = JSONObject(responseBody.toString())
                    val listMovieJSONArray = responseObject.getJSONArray("results")
                    for (i in 0 until listMovieJSONArray.length()) {
                        movieTelevisionObjectList = listMovieJSONArray.getJSONObject(i)
                        movieTelevisionList.add(
                            MovieTVResponse(
                                id = movieTelevisionObjectList.getInt("id"),
                                title = movieTelevisionObjectList.getString("original_title"),
                                overview = movieTelevisionObjectList.getString("overview"),
                                image = "https://image.tmdb.org/t/p/w440_and_h660_face${
                                    movieTelevisionObjectList.getString(
                                        "poster_path"
                                    )
                                }",
                                type = dataStringKey,
                            )
                        )
                    }
                } catch (ex: Exception) {

                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {

            }

        })

        return movieTelevisionList
    }

    fun loadMovieTelevisionData(
        movieTelevisionId: String,
        movieTelevisionType: String
    ): MovieTVResponse {
        urlApi = String()
        if (movieTelevisionType == R.string.movie.toString()) {
            urlApi =
                "https://api.themoviedb.org/3/movie/$movieTelevisionId?api_key=$apiKey&language=en-US"
        } else if (movieTelevisionType == R.string.tv_show.toString()) {
            urlApi =
                "https://api.themoviedb.org/3/tv/$movieTelevisionId?api_key=$apiKey&language=en-US"
        }
        val movieTelevisionData = MovieTVResponse()
        client.get(urlApi, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val responseObject = JSONObject(responseBody.toString())
                    movieTelevisionData.id = responseObject.getInt("id")
                    movieTelevisionData.image =
                        "https://image.tmdb.org/t/p/w440_and_h660_face${responseObject.getString("poster_path")}"
                    movieTelevisionData.title = responseObject.getString("title")
                    movieTelevisionData.year = responseObject.getString("release_date")
                    movieTelevisionData.genre = responseObject.getString("title")
                    movieTelevisionData.duration = responseObject.getDouble("runtime")
                    movieTelevisionData.rating = responseObject.getDouble("vote_average")
                    movieTelevisionData.quote = responseObject.getString("tagline")
                    movieTelevisionData.overview = responseObject.getString("overview")
                    movieTelevisionData.status = responseObject.getString("status")
                    movieTelevisionData.language = responseObject.getString("title")
                    movieTelevisionData.budget = responseObject.getDouble("budget")
                    movieTelevisionData.revenue = responseObject.getDouble("revenue")
                    movieTelevisionData.type = movieTelevisionType
                } catch (ex: Exception) {

                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {

            }

        })
        return movieTelevisionData
    }
}