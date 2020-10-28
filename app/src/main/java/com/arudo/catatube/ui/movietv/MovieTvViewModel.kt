package com.arudo.catatube.ui.movietv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.R
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieTVEntity

class MovieTvViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getMoviesTelevisions(dataStringKey: String): LiveData<ArrayList<MovieTVEntity>>? {
        return when {
            (dataStringKey == R.string.movie.toString()) -> cataTubeRepository.getMoviesList(
                dataStringKey
            )
            (dataStringKey == R.string.tv_show.toString()) -> cataTubeRepository.getTelevisionsList(
                dataStringKey
            )
            else -> null
        }
    }
}