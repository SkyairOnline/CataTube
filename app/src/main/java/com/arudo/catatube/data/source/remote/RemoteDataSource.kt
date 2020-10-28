package com.arudo.catatube.data.source.remote

import android.os.Handler
import com.arudo.catatube.data.source.remote.response.MovieListResponse
import com.arudo.catatube.data.source.remote.response.MovieResponse
import com.arudo.catatube.data.source.remote.response.TVListResponse
import com.arudo.catatube.data.source.remote.response.TVResponse
import com.arudo.catatube.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {
        @Volatile
        private var remoteDataSource: RemoteDataSource? = null
        fun getRemoteDataSource(helper: JsonHelper): RemoteDataSource =
            remoteDataSource ?: synchronized(this) {
                remoteDataSource ?: RemoteDataSource(helper)
            }
    }

    fun getMoviesList(
        loadMovieListCallback: LoadMovieListCallback
    ) = handler.post {
        loadMovieListCallback.onAllMoviesListReceived(jsonHelper.loadListMovies())
    }

    fun getTelevisionsList(
        loadTelevisionListCallback: LoadTelevisionListCallBack
    ) = handler.post {
        loadTelevisionListCallback.onAllTelevisionsListReceived(jsonHelper.loadListTelevision())
    }

    fun getMovieData(
        movieId: Int,
        loadMovieDataCallBack: LoadMovieDataCallBack
    ) = handler.post {
        loadMovieDataCallBack.onMovieDataReceived(jsonHelper.loadMovie(movieId))
    }

    fun getTelevisionData(
        televisionId: Int,
        loadTelevisionDataCallBack: LoadTelevisionDataCallBack
    ) = handler.post {
        loadTelevisionDataCallBack.onTelevisionDataReceived(jsonHelper.loadTelevision(televisionId))
    }

    interface LoadMovieListCallback {
        fun onAllMoviesListReceived(movieListResponse: MovieListResponse)
    }

    interface LoadTelevisionListCallBack {
        fun onAllTelevisionsListReceived(televisionListResponse: TVListResponse)
    }

    interface LoadMovieDataCallBack {
        fun onMovieDataReceived(movieResponse: MovieResponse)
    }

    interface LoadTelevisionDataCallBack {
        fun onTelevisionDataReceived(televisionResponse: TVResponse)
    }
}