package com.arudo.catatube.data.source

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arudo.catatube.data.source.remote.response.*
import com.arudo.catatube.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CataTubeRepository {

    private val apiKey = "f895c2153f5a11853f009558d0b0ee2a"

    companion object {
        @Volatile
        private var cataTubeRepository: CataTubeRepository? = null

        fun getInstance(): CataTubeRepository {
            return cataTubeRepository ?: synchronized(this) {
                cataTubeRepository ?: CataTubeRepository()
            }
        }
    }

    fun getMoviesList(): LiveData<ArrayList<MovieResultsItem>> {
        val movieListResult = MutableLiveData<ArrayList<MovieResultsItem>>()
        val client = ApiConfig.getApiService()
            .getMovieList(apiKey, "en-US", "popularity.desc", "false", "false", "1")
        client.enqueue(
            object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    movieListResult.postValue(response.body()?.results)
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            }
        )
        return movieListResult
    }

    fun getTelevisionsList(): LiveData<ArrayList<TelevisionResultsItem>> {
        val televisionListResult = MutableLiveData<ArrayList<TelevisionResultsItem>>()
        val client = ApiConfig.getApiService().getTelevisionList(
            apiKey,
            "en-US",
            "popularity.desc",
            "1",
            "America%2FNew_York",
            "false"
        )
        client.enqueue(
            object : Callback<TVListResponse> {
                override fun onResponse(
                    call: Call<TVListResponse>,
                    response: Response<TVListResponse>
                ) {
                    televisionListResult.postValue(response.body()?.results)
                }

                override fun onFailure(call: Call<TVListResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            }
        )
        return televisionListResult
    }

    fun getMovieData(movieId: Int): LiveData<MovieResponse> {
        val movieDataResult = MutableLiveData<MovieResponse>()
        val client = ApiConfig.getApiService().getMovieData(movieId, apiKey, "en-US")
        client.enqueue(
            object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    movieDataResult.postValue(response.body())
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            }
        )
        return movieDataResult
    }

    fun getTelevisionData(televisionId: Int): LiveData<TVResponse> {
        val televisionDataResult = MutableLiveData<TVResponse>()
        val client = ApiConfig.getApiService().getTelevisionData(televisionId, apiKey, "en-US")
        client.enqueue(
            object : Callback<TVResponse> {
                override fun onResponse(
                    call: Call<TVResponse>,
                    response: Response<TVResponse>
                ) {
                    televisionDataResult.postValue(response.body())
                }

                override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            }
        )
        return televisionDataResult
    }
}