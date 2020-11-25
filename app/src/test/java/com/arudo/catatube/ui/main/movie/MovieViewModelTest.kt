package com.arudo.catatube.ui.main.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.TestCoroutineRule
import com.arudo.catatube.vo.Resource
import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<MovieResultsItem>>>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(cataTubeRepository)
    }

    @Test
    fun testGetMovieList() = testCoroutineRule.runBlockingTest {
        val dummyMovieList = Resource.success(DataDummy.movieListDummyData())
        val movieList = MutableLiveData<Resource<List<MovieResultsItem>>>()
        movieList.value = dummyMovieList
        `when`(cataTubeRepository.getMoviesList()).thenReturn(movieList)
        val movieListData = movieViewModel.getMovieList().value
        verify(cataTubeRepository).getMoviesList()
        assertNotNull(movieListData)
        assertEquals(2, movieListData?.data?.size)
        movieViewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovieList)
    }

    @Test
    fun testGetNullMovieList() = testCoroutineRule.runBlockingTest {
        val dummyMovieList = null
        val movieList = MutableLiveData<Resource<List<MovieResultsItem>>>()
        movieList.value = dummyMovieList
        `when`(cataTubeRepository.getMoviesList()).thenReturn(movieList)
        val movieListData = movieViewModel.getMovieList().value
        verify(cataTubeRepository).getMoviesList()
        assertNull(movieListData)
        movieViewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyMovieList)
    }
}