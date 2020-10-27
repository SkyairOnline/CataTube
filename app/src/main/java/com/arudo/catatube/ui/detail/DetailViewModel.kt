package com.arudo.catatube.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieTVEntity

class DetailViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    private lateinit var movieTelevisionId: String
    private lateinit var movieTelevisionType: String

    fun setDetailMovieTelevision(movieTelevisionId: String, movieTelevisionType: String) {
        this.movieTelevisionId = movieTelevisionId
        this.movieTelevisionType = movieTelevisionType
    }

    fun getDetailMovieTelevision(): LiveData<MovieTVEntity> =
        cataTubeRepository.getMovieTelevisionData(movieTelevisionId, movieTelevisionType)
}