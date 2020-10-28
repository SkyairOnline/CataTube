package com.arudo.catatube.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arudo.catatube.R
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieTVEntity
import kotlin.properties.Delegates

class DetailViewModel(private val cataTubeRepository: CataTubeRepository) : ViewModel() {
    private var movieTelevisionId by Delegates.notNull<Int>()
    private lateinit var movieTelevisionType: String

    fun setDetailMovieTelevision(movieTelevisionId: Int, movieTelevisionType: String) {
        this.movieTelevisionId = movieTelevisionId
        this.movieTelevisionType = movieTelevisionType
    }

    fun getDetailMovieTelevision(): LiveData<MovieTVEntity>? {
        return when {
            (movieTelevisionType == R.string.movie.toString()) -> cataTubeRepository.getMovieData(
                movieTelevisionId,
                movieTelevisionType
            )
            (movieTelevisionType == R.string.tv_show.toString()) -> cataTubeRepository.getTelevisionData(
                movieTelevisionId,
                movieTelevisionType
            )
            else -> null
        }

    }
}