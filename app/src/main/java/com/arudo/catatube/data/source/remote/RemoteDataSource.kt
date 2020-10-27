package com.arudo.catatube.data.source.remote

import android.os.Handler
import com.arudo.catatube.data.source.remote.response.MovieTVResponse
import com.arudo.catatube.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {
        private const val serviceLatency: Long = 2000

        @Volatile
        private var remoteDataSource: RemoteDataSource? = null
        fun getRemoteDataSource(helper: JsonHelper): RemoteDataSource =
            remoteDataSource ?: synchronized(this) {
                remoteDataSource ?: RemoteDataSource(helper)
            }
    }

    fun getMoviesTelevisionsList(
        dataStringKey: String,
        loadMovieTelevisionListCallback: LoadMovieTelevisionListCallback
    ) = handler.postDelayed({
        loadMovieTelevisionListCallback.onAllMoviesTelevisionsListReceived(
            jsonHelper.loadListMoviesTelevisionData(
                dataStringKey
            )
        )
    }, serviceLatency)

    fun getMovieTelevisionData(
        movieTelevisionId: String,
        movieTelevisionType: String,
        loadMovieTelevisionDataCallback: LoadMovieTelevisionDataCallback
    ) = handler.postDelayed({
        loadMovieTelevisionDataCallback.onMoviesTelevisionDataReceived(
            jsonHelper.loadMovieTelevisionData(movieTelevisionId, movieTelevisionType)
        )
    }, serviceLatency)

    interface LoadMovieTelevisionListCallback {
        fun onAllMoviesTelevisionsListReceived(movieTelevisionListResponse: ArrayList<MovieTVResponse>)
    }

    interface LoadMovieTelevisionDataCallback {
        fun onMoviesTelevisionDataReceived(movieTelevisionDataResponse: MovieTVResponse)
    }
}