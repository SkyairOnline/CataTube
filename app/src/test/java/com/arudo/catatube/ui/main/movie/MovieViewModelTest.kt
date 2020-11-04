package com.arudo.catatube.ui.main.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.utils.DataDummy
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<MovieResultsItem>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(cataTubeRepository)
    }

    @Test
    fun testGetMovieList() {
        val dummyMovieList = DataDummy.movieListDummyData()
        val movieList = MutableLiveData<ArrayList<MovieResultsItem>>()
        movieList.value = dummyMovieList
        `when`(cataTubeRepository.getMoviesList()).thenReturn(movieList)
        val movieListData = movieViewModel.getMovieList().value
        verify(cataTubeRepository).getMoviesList()
        assertNotNull(movieListData)
        assertEquals(20, movieListData?.size)
        movieViewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovieList)
    }

    @Test
    fun testGetZeroMovieList() {
        val dummyMovieList = ArrayList<MovieResultsItem>()
        val movieList = MutableLiveData<ArrayList<MovieResultsItem>>()
        movieList.value = dummyMovieList
        `when`(cataTubeRepository.getMoviesList()).thenReturn(movieList)
        val movieListData = movieViewModel.getMovieList().value
        verify(cataTubeRepository).getMoviesList()
        assertNotNull(movieListData)
        assertEquals(0, movieListData?.size)
        movieViewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovieList)
    }

    @Test
    fun testGetNullMovieList() {
        val dummyMovieList = null
        val movieList = MutableLiveData<ArrayList<MovieResultsItem>>()
        movieList.value = dummyMovieList
        `when`(cataTubeRepository.getMoviesList()).thenReturn(movieList)
        val movieListData = movieViewModel.getMovieList().value
        verify(cataTubeRepository).getMoviesList()
        assertNull(movieListData)
        movieViewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovieList)
    }
}