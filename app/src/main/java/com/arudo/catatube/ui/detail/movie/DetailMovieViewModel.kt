package com.arudo.catatube.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.vo.Resource

class DetailMovieViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    private val movieId = MutableLiveData<Int>()

    fun setDetailMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun getDetailMovie(): LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) {
        cataTubeRepository.getMovieData(it)
    }
}