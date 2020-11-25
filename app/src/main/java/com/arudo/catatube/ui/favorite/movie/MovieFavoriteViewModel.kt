package com.arudo.catatube.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.utils.SortUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MovieFavoriteViewModel(
    private val cataTubeRepository: CataTubeRepository,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    fun getFavoriteMovieList(filter: String): LiveData<PagedList<MovieEntity>> =
        if (filter == SortUtils.newest) {
            cataTubeRepository.getFavoriteMovieListNewest()
        } else {
            cataTubeRepository.getFavoriteMovieListOldest()
        }

    fun deleteFavoriteMovie(movieEntity: MovieEntity) = viewModelScope.launch(defaultDispatcher) {
        cataTubeRepository.deleteMovieFavorite(movieEntity.id)
    }
}