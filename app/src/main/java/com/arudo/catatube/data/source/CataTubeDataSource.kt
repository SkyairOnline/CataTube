package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import com.arudo.catatube.data.source.local.entity.MovieTVEntity

interface CataTubeDataSource {
    fun getMoviesList(dataStringKey: String): LiveData<ArrayList<MovieTVEntity>>
    fun getTelevisionsList(dataStringKey: String): LiveData<ArrayList<MovieTVEntity>>
    fun getMovieData(
        movieId: Int,
        movieType: String
    ): LiveData<MovieTVEntity>

    fun getTelevisionData(
        televisionId: Int,
        televisionType: String
    ): LiveData<MovieTVEntity>
}