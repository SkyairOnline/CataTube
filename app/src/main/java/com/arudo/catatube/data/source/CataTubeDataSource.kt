package com.arudo.catatube.data.source

import androidx.lifecycle.LiveData
import com.arudo.catatube.data.source.local.entity.MovieTVEntity

interface CataTubeDataSource {
    fun getMoviesTelevisionsList(dataStringKey: String): LiveData<ArrayList<MovieTVEntity>>
    fun getMovieTelevisionData(
        movieTelevisionId: String,
        movieTelevisionType: String
    ): LiveData<MovieTVEntity>
}