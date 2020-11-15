package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import com.arudo.catatube.BuildConfig
import com.arudo.catatube.data.source.local.LocalDataSource
import com.arudo.catatube.data.source.local.entity.*
import com.arudo.catatube.data.source.remote.AppExcecutors
import com.arudo.catatube.data.source.remote.response.ApiResponse
import com.arudo.catatube.data.source.remote.utils.ApiService
import com.arudo.catatube.data.vo.Resource

class CataTubeRepository private constructor(
    private val appExcecutors: AppExcecutors,
    private val localDataSource: LocalDataSource,
    private val apiService: ApiService
) {
    private val apiKey = BuildConfig.API_KEY

    companion object {
        @Volatile
        private var cataTubeRepository: CataTubeRepository? = null

        fun getInstance(
            appExcecutors: AppExcecutors,
            localDataSource: LocalDataSource,
            apiService: ApiService
        ): CataTubeRepository {
            return cataTubeRepository ?: synchronized(this) {
                cataTubeRepository ?: CataTubeRepository(appExcecutors, localDataSource, apiService)
            }
        }
    }

    fun getMoviesList(): LiveData<Resource<List<MovieResultsItem>>> {
        return object :
            NetworkBoundResource<List<MovieResultsItem>, MovieListEntity>(appExcecutors) {
            override fun loadFromDatabase(): LiveData<List<MovieResultsItem>> =
                localDataSource.getMovieList()

            override fun shouldFetch(data: List<MovieResultsItem>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MovieListEntity>> =
                apiService.getMovieList(apiKey, "en-US", "popularity.desc", "false", "false", "1")

            override fun saveCallResult(data: MovieListEntity) =
                localDataSource.insertMovieList(data.results)

        }.asLiveData()
    }

    fun getTelevisionsList(): LiveData<Resource<List<TelevisionResultsItem>>> {
        return object :
            NetworkBoundResource<List<TelevisionResultsItem>, TVListEntity>(appExcecutors) {
            override fun loadFromDatabase(): LiveData<List<TelevisionResultsItem>> =
                localDataSource.getTelevisionList()

            override fun shouldFetch(data: List<TelevisionResultsItem>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<TVListEntity>> =
                apiService.getTelevisionList(
                    apiKey,
                    "en-US",
                    "popularity.desc",
                    "1",
                    "America%2FNew_York",
                    "false"
                )

            override fun saveCallResult(data: TVListEntity) =
                localDataSource.insertTelevisionList(data.results)

        }.asLiveData()
    }

    fun getMovieData(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieEntity>(appExcecutors) {
            override fun loadFromDatabase(): LiveData<MovieEntity> =
                localDataSource.getMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MovieEntity>> =
                apiService.getMovieData(movieId, apiKey, "en-US")

            override fun saveCallResult(data: MovieEntity) = localDataSource.insertMovie(data)

        }.asLiveData()
    }

    fun getTelevisionData(televisionId: Int): LiveData<Resource<TVEntity>> {
        return object : NetworkBoundResource<TVEntity, TVEntity>(appExcecutors) {
            override fun loadFromDatabase(): LiveData<TVEntity> =
                localDataSource.getTelevision(televisionId)

            override fun shouldFetch(data: TVEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TVEntity>> =
                apiService.getTelevisionData(televisionId, apiKey, "en-US")

            override fun saveCallResult(data: TVEntity) = localDataSource.insertTelevision(data)

        }.asLiveData()
    }
}