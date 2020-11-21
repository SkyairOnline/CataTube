package com.arudo.catatube.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieEntity
import kotlinx.coroutines.launch

class MovieFavoriteViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getFavoriteMovieList(): LiveData<PagedList<MovieEntity>> =
        cataTubeRepository.getFavoriteMovieList()

    fun deleteFavoriteMovie(movieEntity: MovieEntity) = viewModelScope.launch {
        cataTubeRepository.deleteMovieFavorite(movieEntity.id)
    }
}