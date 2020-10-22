package com.arudo.catatube.ui.detail

import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.MovieTVEntity
import com.arudo.catatube.utils.DataDummy

class DetailViewModel : ViewModel() {
    private lateinit var movieTelevisionId: String
    private lateinit var movieTelevision: MovieTVEntity

    fun setDetailMovieTelevision(movieTelevisionId: String) {
        this.movieTelevisionId = movieTelevisionId
    }

    fun getDetailMovieTelevision(): MovieTVEntity {
        var movieTelevisionItems = ArrayList<MovieTVEntity>()
        if (movieTelevisionId.startsWith('M')) {
            movieTelevisionItems = DataDummy.movieDummyData()
        } else if (movieTelevisionId.startsWith('T')) {
            movieTelevisionItems = DataDummy.televisionDummyData()
        }
        for (m in movieTelevisionItems) {
            if (m.id == movieTelevisionId) {
                movieTelevision = m
            }
        }
        return movieTelevision
    }
}