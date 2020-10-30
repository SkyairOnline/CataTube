package com.arudo.catatube.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.remote.response.MovieResponse
import kotlin.properties.Delegates

class DetailMovieViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    private var movieId by Delegates.notNull<Int>()

    fun setDetailMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getDetailMovie(): LiveData<MovieResponse> = cataTubeRepository.getMovieData(movieId)

}