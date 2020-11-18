package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import com.arudo.catatube.data.source.local.LocalDataSource
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.utils.NetworkBoundResource
import com.arudo.catatube.vo.Resource

class CataTubeRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    companion object {
        @Volatile
        private var cataTubeRepository: CataTubeRepository? = null

        fun getInstance(
            localDataSource: LocalDataSource,
            remoteDataSource: RemoteDataSource
        ): CataTubeRepository {
            return cataTubeRepository ?: synchronized(this) {
                cataTubeRepository ?: CataTubeRepository(localDataSource, remoteDataSource)
            }
        }
    }

    fun getMoviesList(): LiveData<Resource<List<MovieResultsItem>>> {
        return NetworkBoundResource(
            { localDataSource.getMovieList() },
            { remoteDataSource.getMovieList() },
            { localDataSource.insertMovieList(it.results) })
    }

    fun getTelevisionsList(): LiveData<Resource<List<TelevisionResultsItem>>> {
        return NetworkBoundResource(
            { localDataSource.getTelevisionList() },
            { remoteDataSource.getTelevisionList() },
            { localDataSource.insertTelevisionList(it.results) })
    }

    fun getMovieData(movieId: Int): LiveData<Resource<MovieEntity>> {
        return NetworkBoundResource(
            { localDataSource.getMovie(movieId) },
            { remoteDataSource.getMovieData(movieId) },
            { localDataSource.insertMovie(it) })
    }

    fun getTelevisionData(televisionId: Int): LiveData<Resource<TVEntity>> {
        return NetworkBoundResource(
            { localDataSource.getTelevision(televisionId) },
            { remoteDataSource.getTelevisionData(televisionId) },
            { localDataSource.insertTelevision(it) })
    }
}