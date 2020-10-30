package com.arudo.catatube.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.remote.response.MovieResultsItem

class MovieViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getMovieList(): LiveData<ArrayList<MovieResultsItem>> = cataTubeRepository.getMoviesList()
}