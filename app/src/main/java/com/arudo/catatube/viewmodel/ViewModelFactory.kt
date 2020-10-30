package com.arudo.catatube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.di.Injection
import com.arudo.catatube.ui.detail.movie.DetailMovieViewModel
import com.arudo.catatube.ui.detail.tv.DetailTelevisionViewModel
import com.arudo.catatube.ui.main.movie.MovieViewModel
import com.arudo.catatube.ui.main.tv.TvViewModel

class ViewModelFactory private constructor(private val cataTubeRepository: CataTubeRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var viewModelFactory: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory {
            return viewModelFactory ?: synchronized(this) {
                viewModelFactory ?: ViewModelFactory(Injection.provideRepository())
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(cataTubeRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(cataTubeRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(cataTubeRepository) as T
            }
            modelClass.isAssignableFrom(DetailTelevisionViewModel::class.java) -> {
                DetailTelevisionViewModel(cataTubeRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}