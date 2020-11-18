package com.arudo.catatube.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.vo.Resource

class MovieViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    fun getMovieList(): LiveData<Resource<List<MovieResultsItem>>> =
        cataTubeRepository.getMoviesList()
}