package com.arudo.catatube.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val dummyDataMovie = DataDummy.movieDummyData()[0]
    private val movieId = dummyDataMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        detailMovieViewModel = DetailMovieViewModel(cataTubeRepository)
        detailMovieViewModel.setDetailMovie(movieId)
    }

    @Test
    fun testGetDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyDataMovie
        `when`(cataTubeRepository.getMovieData(movieId)).thenReturn(movie)
        val movieData = detailMovieViewModel.getDetailMovie().value
        verify(cataTubeRepository).getMovieData(movieId)
        assertNotNull(movieData)
        assertEquals(dummyDataMovie.overview, movieData?.overview)
        assertEquals(dummyDataMovie.releaseDate, movieData?.releaseDate)
        assertEquals(dummyDataMovie.voteAverage, movieData?.voteAverage)
        assertEquals(dummyDataMovie.runtime, movieData?.runtime)
        assertEquals(dummyDataMovie.tagline, movieData?.tagline)
        assertEquals(dummyDataMovie.id, movieData?.id)
        assertEquals(dummyDataMovie.title, movieData?.title)
        assertEquals(dummyDataMovie.status, movieData?.status)
        assertEquals(dummyDataMovie.posterPath, movieData?.posterPath)
        assertEquals(dummyDataMovie.budget, movieData?.budget)
        assertEquals(dummyDataMovie.revenue, movieData?.revenue)
        detailMovieViewModel.getDetailMovie().observeForever(observer)
        verify(observer).onChanged(dummyDataMovie)
    }
}