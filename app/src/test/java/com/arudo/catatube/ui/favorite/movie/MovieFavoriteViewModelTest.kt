package com.arudo.catatube.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.SortUtils
import com.arudo.catatube.utils.TestCoroutineRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
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
class MovieFavoriteViewModelTest {

    private lateinit var movieFavoriteViewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        movieFavoriteViewModel =
            MovieFavoriteViewModel(cataTubeRepository, testCoroutineRule.testCoroutineDispatcher)
    }

    @Test
    fun testFavoriteListMovieNewest() = testCoroutineRule.runBlockingTest {
        val dummyMovieFavoriteList = moviePagedList
        `when`(dummyMovieFavoriteList.size).thenReturn(3)
        val movieFavoriteList = MutableLiveData<PagedList<MovieEntity>>()
        movieFavoriteList.value = dummyMovieFavoriteList
        `when`(cataTubeRepository.getFavoriteMovieListNewest()).thenReturn(movieFavoriteList)
        val movieFavoriteListEntity =
            movieFavoriteViewModel.getFavoriteMovieList(SortUtils.newest).value
        verify(cataTubeRepository).getFavoriteMovieListNewest()
        assertNotNull(movieFavoriteListEntity)
        assertEquals(3, movieFavoriteListEntity?.size)
        movieFavoriteViewModel.getFavoriteMovieList(SortUtils.newest).observeForever(observer)
        verify(observer).onChanged(dummyMovieFavoriteList)
        movieFavoriteViewModel.deleteFavoriteMovie(DataDummy.movieDummyData()[0])
    }

    @Test
    fun testFavoriteListMovieOldest() = testCoroutineRule.runBlockingTest {
        val dummyMovieFavoriteList = moviePagedList
        `when`(dummyMovieFavoriteList.size).thenReturn(3)
        val movieFavoriteList = MutableLiveData<PagedList<MovieEntity>>()
        movieFavoriteList.value = dummyMovieFavoriteList
        `when`(cataTubeRepository.getFavoriteMovieListOldest()).thenReturn(movieFavoriteList)
        val movieFavoriteListEntity =
            movieFavoriteViewModel.getFavoriteMovieList(SortUtils.oldest).value
        verify(cataTubeRepository).getFavoriteMovieListOldest()
        assertNotNull(movieFavoriteListEntity)
        assertEquals(3, movieFavoriteListEntity?.size)
        movieFavoriteViewModel.getFavoriteMovieList(SortUtils.oldest).observeForever(observer)
        verify(observer).onChanged(dummyMovieFavoriteList)
        movieFavoriteViewModel.deleteFavoriteMovie(DataDummy.movieDummyData()[0])
    }
}