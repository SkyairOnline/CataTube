package com.arudo.catatube.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.FavoriteMovieEntity
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.TestCoroutineRule
import com.arudo.catatube.vo.Resource
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
class DetailMovieViewModelTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val dummyDataMovie = Resource.success(DataDummy.movieDummyData()[0])
    private val movieId = dummyDataMovie.data?.id!!

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var favoriteObserver: Observer<FavoriteMovieEntity>

    @Before
    fun setUp() {
        detailMovieViewModel =
            DetailMovieViewModel(cataTubeRepository, testCoroutineRule.testCoroutineDispatcher)
        detailMovieViewModel.setDetailMovie(movieId)
    }

    @Test
    fun testGetDetailMovie() = testCoroutineRule.runBlockingTest {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDataMovie
        `when`(cataTubeRepository.getMovieData(movieId)).thenReturn(movie)
        detailMovieViewModel.getDetailMovie().observeForever(observer)
        verify(observer).onChanged(dummyDataMovie)
    }

    @Test
    fun testFavoriteMovie() = testCoroutineRule.runBlockingTest {
        detailMovieViewModel.setFavoriteMovie(movieId)
        val dummyMovieData = MutableLiveData<FavoriteMovieEntity>()
        dummyMovieData.value = DataDummy.favoriteMovieDummyData()[0]
        `when`(cataTubeRepository.getFavoriteMovie(movieId)).thenReturn(dummyMovieData)
        val movieData = detailMovieViewModel.getFavoriteMovie(movieId).value
        verify(cataTubeRepository).getFavoriteMovie(movieId)
        assertNotNull(movieData)
        assertEquals(movieId, movieData?.id)
        detailMovieViewModel.getFavoriteMovie(movieId).observeForever(favoriteObserver)
        verify(favoriteObserver).onChanged(DataDummy.favoriteMovieDummyData()[0])
        detailMovieViewModel.deleteFavoriteMovie(movieId)
    }
}