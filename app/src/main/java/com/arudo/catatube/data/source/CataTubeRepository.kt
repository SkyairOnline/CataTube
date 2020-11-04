package com.arudo.catatube.data.source

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arudo.catatube.BuildConfig
import com.arudo.catatube.data.source.local.entity.*
import com.arudo.catatube.data.source.remote.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CataTubeRepository {

    private val apiKey = BuildConfig.API_KEY

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
            object : Callback<MovieListEntity> {
                override fun onResponse(
                    call: Call<MovieListEntity>,
                    response: Response<MovieListEntity>
                ) {
                    movieListResult.postValue(response.body()?.results)
                }

                override fun onFailure(call: Call<MovieListEntity>, t: Throwable) {
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
            object : Callback<TVListEntity> {
                override fun onResponse(
                    call: Call<TVListEntity>,
                    response: Response<TVListEntity>
                ) {
                    televisionListResult.postValue(response.body()?.results)
                }

                override fun onFailure(call: Call<TVListEntity>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            }
        )
        return televisionListResult
    }

    fun getMovieData(movieId: Int): LiveData<MovieEntity> {
        val movieDataResult = MutableLiveData<MovieEntity>()
        val client = ApiConfig.getApiService().getMovieData(movieId, apiKey, "en-US")
        client.enqueue(
            object : Callback<MovieEntity> {
                override fun onResponse(
                    call: Call<MovieEntity>,
                    entity: Response<MovieEntity>
                ) {
                    movieDataResult.postValue(entity.body())
                }

                override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            }
        )
        return movieDataResult
    }

    fun getTelevisionData(televisionId: Int): LiveData<TVEntity> {
        val televisionDataResult = MutableLiveData<TVEntity>()
        val client = ApiConfig.getApiService().getTelevisionData(televisionId, apiKey, "en-US")
        client.enqueue(
            object : Callback<TVEntity> {
                override fun onResponse(
                    call: Call<TVEntity>,
                    entity: Response<TVEntity>
                ) {
                    televisionDataResult.postValue(entity.body())
                }

                override fun onFailure(call: Call<TVEntity>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}")
                }
            }
        )
        return televisionDataResult
    }
}