package com.arudo.catatube.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.di.Injection
import com.arudo.catatube.ui.detail.DetailViewModel
import com.arudo.catatube.ui.movietv.MovieTvViewModel

class ViewModelFactory private constructor(private val cataTubeRepository: CataTubeRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var viewModelFactory: ViewModelFactory? = null
        fun getViewModelFactory(): ViewModelFactory = viewModelFactory ?: synchronized(this) {
            viewModelFactory ?: synchronized(this) {
                viewModelFactory ?: ViewModelFactory(Injection.provideRepository())
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieTvViewModel::class.java) -> {
                MovieTvViewModel(cataTubeRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(cataTubeRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}