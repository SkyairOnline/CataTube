package com.arudo.catatube.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieEntity

class MovieFavoriteViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getFavoriteMovieList(): LiveData<List<MovieEntity>> =
        cataTubeRepository.getFavoriteMovieList()
}