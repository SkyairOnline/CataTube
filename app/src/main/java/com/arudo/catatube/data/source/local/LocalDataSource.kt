package com.arudo.catatube.data.source.local

import androidx.lifecycle.LiveData
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem
import com.arudo.catatube.data.source.local.room.CataTubeDao

class LocalDataSource private constructor(private val cataTubeDao: CataTubeDao) {
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

    fun insertMovie(movie: MovieEntity) = cataTubeDao.insertMovie(movie)

    fun getTelevision(id: Int): LiveData<TVEntity> = cataTubeDao.getTelevision(id)

    fun insertTelevision(television: TVEntity) = cataTubeDao.insertTelevision(television)
}