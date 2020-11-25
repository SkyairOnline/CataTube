package com.arudo.catatube.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.arudo.catatube.data.source.local.entity.*
import com.arudo.catatube.data.source.local.room.CataTubeDao

class LocalDataSource(private val cataTubeDao: CataTubeDao) {
    companion object {
        private var localDataSource: LocalDataSource? = null
        fun getInstance(cataTubeDao: CataTubeDao): LocalDataSource {
            return localDataSource ?: LocalDataSource(cataTubeDao)
        }
    }

    fun getMovieList(): LiveData<List<MovieResultsItem>> = cataTubeDao.getMovieList()

    fun insertMovieList(movieList: ArrayList<MovieResultsItem>) =
        cataTubeDao.insertMovieList(movieList)

    fun getTelevisionList(): LiveData<List<TelevisionResultsItem>> = cataTubeDao.getTelevisionList()

    fun insertTelevisionList(televisionList: ArrayList<TelevisionResultsItem>) =
        cataTubeDao.insertTelevisionList(televisionList)

    fun getMovie(id: Int): LiveData<MovieEntity> = cataTubeDao.getMovie(id)

    fun getMovieFavorite(id: Int): LiveData<FavoriteMovieEntity> = cataTubeDao.getMovieFavorite(id)

    fun insertMovie(movie: MovieEntity) = cataTubeDao.insertMovie(movie)

    fun getTelevision(id: Int): LiveData<TVEntity> = cataTubeDao.getTelevision(id)

    fun getTelevisionFavorite(id: Int): LiveData<FavoriteTelevisionEntity> =
        cataTubeDao.getTelevisionFavorite(id)

    fun insertTelevision(television: TVEntity) = cataTubeDao.insertTelevision(television)

    fun getMovieFavoriteListNewest(): DataSource.Factory<Int, MovieEntity> =
        cataTubeDao.getMovieFavoriteListNewest()

    fun getMovieFavoriteListOldest(): DataSource.Factory<Int, MovieEntity> =
        cataTubeDao.getMovieFavoriteListOldest()

    fun getTelevisionFavoriteListNewest(): DataSource.Factory<Int, TVEntity> =
        cataTubeDao.getTelevisionFavoriteListNewest()

    fun getTelevisionFavoriteListOldest(): DataSource.Factory<Int, TVEntity> =
        cataTubeDao.getTelevisionFavoriteListOldest()

    suspend fun insertMovieFavorite(movieId: Int) {
        cataTubeDao.insertFavoriteMovie(FavoriteMovieEntity(movieId))
    }

    suspend fun insertTelevisionFavorite(televisionId: Int) {
        cataTubeDao.insertFavoritTelevision(FavoriteTelevisionEntity(televisionId))
    }

    suspend fun deleteMovieFavorite(movieId: Int) {
        cataTubeDao.deleteFavoriteMovie(FavoriteMovieEntity(movieId))
    }

    suspend fun deleteTelevisionFavorite(televisionId: Int) {
        cataTubeDao.deleteFavoriteTelevision(FavoriteTelevisionEntity(televisionId))
    }
}