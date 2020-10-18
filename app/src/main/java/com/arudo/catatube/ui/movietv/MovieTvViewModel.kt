package com.arudo.catatube.ui.movietv

import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.MovieTVEntity
import com.arudo.catatube.utils.DataDummy

class MovieTvViewModel : ViewModel() {
    fun getMovies(): ArrayList<MovieTVEntity> = DataDummy.movieDummyData()
    fun getTelevisions(): ArrayList<MovieTVEntity> = DataDummy.televisionDummyData()
}