package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arudo.catatube.data.source.local.LocalDataSource
import com.arudo.catatube.data.source.local.entity.*
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.utils.NetworkBoundResource
import com.arudo.catatube.vo.Resource
import kotlinx.coroutines.CoroutineDispatcher

class CataTubeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    companion object {
        @Volatile
        private var cataTubeRepository: CataTubeRepository? = null

        fun getInstance(
            localDataSource: LocalDataSource,
            remoteDataSource: RemoteDataSource,
            coroutineDispatcher: CoroutineDispatcher
        ): CataTubeRepository {
            return cataTubeRepository ?: synchronized(this) {
                cataTubeRepository ?: CataTubeRepository(
                    localDataSource,
                    remoteDataSource,
                    coroutineDispatcher
                )
            }
        }
    }

    fun getMoviesList(): LiveData<Resource<List<MovieResultsItem>>> {
        return NetworkBoundResource(
            { localDataSource.getMovieList() },
            { remoteDataSource.getMovieList() },
            { localDataSource.insertMovieList(it.results) },
            coroutineDispatcher
        )
    }

    fun getFavoriteMovieListNewest(): LiveData<PagedList<MovieEntity>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(20)
                .setPageSize(20).build()
        return LivePagedListBuilder(localDataSource.getMovieFavoriteListNewest(), config).build()
    }

    fun getFavoriteMovieListOldest(): LiveData<PagedList<MovieEntity>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(20)
                .setPageSize(20).build()
        return LivePagedListBuilder(localDataSource.getMovieFavoriteListOldest(), config).build()
    }

    fun getFavoriteMovie(movieId: Int): LiveData<FavoriteMovieEntity> =
        localDataSource.getMovieFavorite(movieId)

    fun getTelevisionsList(): LiveData<Resource<List<TelevisionResultsItem>>> {
        return NetworkBoundResource(
            { localDataSource.getTelevisionList() },
            { remoteDataSource.getTelevisionList() },
            { localDataSource.insertTelevisionList(it.results) },
            coroutineDispatcher
        )
    }

    fun getFavoriteTelevisionListNewest(): LiveData<PagedList<TVEntity>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(21)
                .setPageSize(21).build()
        return LivePagedListBuilder(
            localDataSource.getTelevisionFavoriteListNewest(),
            config
        ).build()
    }

    fun getFavoriteTelevisionListOldest(): LiveData<PagedList<TVEntity>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(21)
                .setPageSize(21).build()
        return LivePagedListBuilder(
            localDataSource.getTelevisionFavoriteListOldest(),
            config
        ).build()
    }

    fun getFavoriteTelevision(televisionId: Int): LiveData<FavoriteTelevisionEntity> =
        localDataSource.getTelevisionFavorite(televisionId)

    fun getMovieData(movieId: Int): LiveData<Resource<MovieEntity>> {
        return NetworkBoundResource(
            { localDataSource.getMovie(movieId) },
            { remoteDataSource.getMovieData(movieId) },
            { localDataSource.insertMovie(it) },
            coroutineDispatcher
        )
    }

    fun getTelevisionData(televisionId: Int): LiveData<Resource<TVEntity>> {
        return NetworkBoundResource(
            { localDataSource.getTelevision(televisionId) },
            { remoteDataSource.getTelevisionData(televisionId) },
            { localDataSource.insertTelevision(it) },
            coroutineDispatcher
        )
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