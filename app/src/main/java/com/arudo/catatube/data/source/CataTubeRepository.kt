package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arudo.catatube.data.source.local.LocalDataSource
import com.arudo.catatube.data.source.local.entity.*
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.utils.NetworkBoundResource
import com.arudo.catatube.utils.SortUtils
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

    fun getFavoriteMovieList(filter: String): LiveData<PagedList<MovieEntity>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(20)
                .setPageSize(20).build()
        val query = SortUtils.getSortedQuery(filter, "movieentity", "favoritemovieentity")
        return LivePagedListBuilder(localDataSource.getMovieFavoriteList(query), config).build()
    }

    fun getFavoriteMovie(movieId: Int): LiveData<FavoriteMovieEntity> =
        localDataSource.getMovieFavorite(movieId)

    fun getTelevisionsList(): LiveData<Resource<List<TelevisionResultsItem>>> {
        return NetworkBoundResource(
            { localDataSource.getTelevisionList() },
            { remoteDataSource.getTelevisionList() },
            { localDataSource.insertTelevisionList(it.results) })
    }

    fun getFavoriteTelevisionList(filter: String): LiveData<PagedList<TVEntity>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(21)
                .setPageSize(21).build()
        val query = SortUtils.getSortedQuery(filter, "televisionentity", "favoritetelevisionentity")
        return LivePagedListBuilder(
            localDataSource.getTelevisionFavoriteList(query),
            config
        ).build()
    }

    fun getFavoriteTelevision(televisionId: Int): LiveData<FavoriteTelevisionEntity> =
        localDataSource.getTelevisionFavorite(televisionId)

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

    suspend fun insertMovieFavorite(movieId: Int) {
        localDataSource.insertMovieFavorite(movieId)
    }

    suspend fun insertTelevisionFavorite(televisionId: Int) {
        localDataSource.insertTelevisionFavorite(televisionId)
    }

    suspend fun deleteMovieFavorite(movieId: Int) {
        localDataSource.deleteMovieFavorite(movieId)
    }

    suspend fun deleteTelevisionFavorite(televisionId: Int) {
        localDataSource.deleteTelevisionFavorite(televisionId)
    }
}