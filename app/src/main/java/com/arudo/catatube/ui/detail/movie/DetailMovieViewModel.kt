package com.arudo.catatube.ui.detail.movie

import androidx.lifecycle.*
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.FavoriteMovieEntity
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.vo.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val cataTubeRepository: CataTubeRepository,
    private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val movieId = MutableLiveData<Int>()

    fun setDetailMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun getDetailMovie(): LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) {
        cataTubeRepository.getMovieData(it)
    }

    fun getFavoriteMovie(movieId: Int): LiveData<FavoriteMovieEntity> =
        cataTubeRepository.getFavoriteMovie(movieId)

    fun setFavoriteMovie(movieId: Int) = viewModelScope.launch(defaultDispatcher) {
        cataTubeRepository.insertMovieFavorite(movieId)
    }

    fun deleteFavoriteMovie(movieId: Int) = viewModelScope.launch(defaultDispatcher) {
        cataTubeRepository.deleteMovieFavorite(movieId)
    }
}