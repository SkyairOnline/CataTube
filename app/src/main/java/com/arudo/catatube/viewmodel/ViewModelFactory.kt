package com.arudo.catatube.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.di.Injection
import com.arudo.catatube.ui.detail.movie.DetailMovieViewModel
import com.arudo.catatube.ui.detail.tv.DetailTelevisionViewModel
import com.arudo.catatube.ui.favorite.movie.MovieFavoriteViewModel
import com.arudo.catatube.ui.favorite.tv.TvFavoriteViewModel
import com.arudo.catatube.ui.main.movie.MovieViewModel
import com.arudo.catatube.ui.main.tv.TvViewModel
import kotlinx.coroutines.Dispatchers

class ViewModelFactory private constructor(private val cataTubeRepository: CataTubeRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var viewModelFactory: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            return viewModelFactory ?: synchronized(this) {
                viewModelFactory ?: ViewModelFactory(Injection.provideRepository(context))
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
                DetailMovieViewModel(cataTubeRepository, Dispatchers.Default) as T
            }
            modelClass.isAssignableFrom(DetailTelevisionViewModel::class.java) -> {
                DetailTelevisionViewModel(cataTubeRepository, Dispatchers.Default) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(cataTubeRepository, Dispatchers.Default) as T
            }
            modelClass.isAssignableFrom(TvFavoriteViewModel::class.java) -> {
                TvFavoriteViewModel(cataTubeRepository, Dispatchers.Default) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}