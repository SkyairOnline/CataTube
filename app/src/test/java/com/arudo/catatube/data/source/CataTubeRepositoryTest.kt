package com.arudo.catatube.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.arudo.catatube.data.source.local.LocalDataSource
import com.arudo.catatube.data.source.local.entity.*
import com.arudo.catatube.data.source.remote.RemoteDataSource
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.LiveDataTest
import com.arudo.catatube.utils.PagedListTest
import com.arudo.catatube.utils.TestCoroutineRule
import com.arudo.catatube.vo.Resource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CataTubeRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private lateinit var cataTubeRepository: CataTubeRepository
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        localDataSource = mock(LocalDataSource::class.java)
        remoteDataSource = mock(RemoteDataSource::class.java)
        cataTubeRepository = CataTubeRepository(
            localDataSource,
            remoteDataSource,
            testCoroutineRule.testCoroutineDispatcher
        )
    }

    @Test
    fun getMovieList() = testCoroutineRule.runBlockingTest {
        val data = MovieListEntity(results = ArrayList(DataDummy.movieListDummyData()))
        `when`(remoteDataSource.getMovieList()).thenReturn(Resource.success(data))
        val dummyLocalMovieList = MutableLiveData<List<MovieResultsItem>>()
        dummyLocalMovieList.value = DataDummy.movieListDummyData()
        `when`(localDataSource.getMovieList()).thenReturn(dummyLocalMovieList)
        LiveDataTest.getValue(cataTubeRepository.getMoviesList())
        verify(remoteDataSource).getMovieList()
        verify(localDataSource).getMovieList()
    }

    @Test
    fun getFavoriteMovieListNewest() {
        val movieFavoriteListResponse = DataDummy.movieDummyData()
        val dataSourceFactory = PagedListTest.createMockDataSourceFactory(movieFavoriteListResponse)
        `when`(localDataSource.getMovieFavoriteListNewest()).thenReturn(dataSourceFactory)
        cataTubeRepository.getFavoriteMovieListNewest()
        verify(localDataSource).getMovieFavoriteListNewest()
    }

    @Test
    fun getFavoriteMovieListOldest() {
        val movieFavoriteListResponse = DataDummy.movieDummyData()
        val dataSourceFactory = PagedListTest.createMockDataSourceFactory(movieFavoriteListResponse)
        `when`(localDataSource.getMovieFavoriteListOldest()).thenReturn(dataSourceFactory)
        cataTubeRepository.getFavoriteMovieListOldest()
        verify(localDataSource).getMovieFavoriteListOldest()
    }

    @Test
    fun getFavoriteMovie() {
        val data = MutableLiveData<FavoriteMovieEntity>()
        data.value = DataDummy.favoriteMovieDummyData()[0]
        val id = data.value!!.id
        `when`(localDataSource.getMovieFavorite(id)).thenReturn(data)
        val favoriteMovieEntity = LiveDataTest.getValue(cataTubeRepository.getFavoriteMovie(id))
        verify(localDataSource).getMovieFavorite(id)
        assertNotNull(favoriteMovieEntity)
        assertEquals(id, favoriteMovieEntity.id)
    }

    @Test
    fun getTelevisionList() = testCoroutineRule.runBlockingTest {
        val data = TVListEntity(results = ArrayList(DataDummy.televisionListDummyData()))
        `when`(remoteDataSource.getTelevisionList()).thenReturn(Resource.success(data))
        val dummyLocalTelevisionList = MutableLiveData<List<TelevisionResultsItem>>()
        dummyLocalTelevisionList.value = DataDummy.televisionListDummyData()
        `when`(localDataSource.getTelevisionList()).thenReturn(dummyLocalTelevisionList)
        LiveDataTest.getValue(cataTubeRepository.getTelevisionsList())
        verify(remoteDataSource).getTelevisionList()
        verify(localDataSource).getTelevisionList()
    }

    @Test
    fun getFavoriteTelevisionListNewest() {
        val televisionFavoriteListResponse = DataDummy.televisionDummyData()
        val dataSourceFactory =
            PagedListTest.createMockDataSourceFactory(televisionFavoriteListResponse)
        `when`(localDataSource.getTelevisionFavoriteListNewest()).thenReturn(dataSourceFactory)
        cataTubeRepository.getFavoriteTelevisionListNewest()
        verify(localDataSource).getTelevisionFavoriteListNewest()
    }

    @Test
    fun getFavoriteTelevisionListOldest() {
        val televisionFavoriteListResponse = DataDummy.televisionDummyData()
        val dataSourceFactory =
            PagedListTest.createMockDataSourceFactory(televisionFavoriteListResponse)
        `when`(localDataSource.getTelevisionFavoriteListOldest()).thenReturn(dataSourceFactory)
        cataTubeRepository.getFavoriteTelevisionListOldest()
        verify(localDataSource).getTelevisionFavoriteListOldest()
    }

    @Test
    fun getFavoriteTelevision() {
        val data = MutableLiveData<FavoriteTelevisionEntity>()
        data.value = DataDummy.favoriteTelevisionDummyData()[0]
        val id = data.value!!.id
        `when`(localDataSource.getTelevisionFavorite(id)).thenReturn(data)
        val favoriteTelevisionEntity =
            LiveDataTest.getValue(cataTubeRepository.getFavoriteTelevision(id))
        verify(localDataSource).getTelevisionFavorite(id)
        assertNotNull(favoriteTelevisionEntity)
        assertEquals(id, favoriteTelevisionEntity.id)
    }

    @Test
    fun getMovieData() = testCoroutineRule.runBlockingTest {
        val data = DataDummy.movieDummyData()[0]
        val id = data.id
        val dummyLocalMovieList = MutableLiveData<MovieEntity>()
        dummyLocalMovieList.value = data
        `when`(localDataSource.getMovie(id)).thenReturn(dummyLocalMovieList)
        `when`(remoteDataSource.getMovieData(id)).thenReturn(Resource.success(data))
        LiveDataTest.getValue(cataTubeRepository.getMovieData(id))
        verify(localDataSource).getMovie(id)
        verify(remoteDataSource).getMovieData(id)
    }

    @Test
    fun getTelevisionData() = testCoroutineRule.runBlockingTest {
        val data = DataDummy.televisionDummyData()[0]
        val id = data.id
        val dummyLocalTelevisionList = MutableLiveData<TVEntity>()
        dummyLocalTelevisionList.value = data
        `when`(localDataSource.getTelevision(id)).thenReturn(dummyLocalTelevisionList)
        `when`(remoteDataSource.getTelevisionData(id)).thenReturn(Resource.success(data))
        LiveDataTest.getValue(cataTubeRepository.getTelevisionData(id))
        verify(localDataSource).getTelevision(id)
        verify(remoteDataSource).getTelevisionData(id)
    }

    @Test
    fun insertMovieFavorite() = testCoroutineRule.runBlockingTest {
        val data = MutableLiveData<FavoriteMovieEntity>()
        data.value = DataDummy.favoriteMovieDummyData()[0]
        val id = data.value!!.id
        cataTubeRepository.insertMovieFavorite(id)
    }

    @Test
    fun deleteMovieFavorite() = testCoroutineRule.runBlockingTest {
        val data = MutableLiveData<FavoriteMovieEntity>()
        data.value = DataDummy.favoriteMovieDummyData()[0]
        val id = data.value!!.id
        cataTubeRepository.deleteMovieFavorite(id)
    }


    @Test
    fun insertTelevisionFavorite() = testCoroutineRule.runBlockingTest {
        val data = MutableLiveData<FavoriteTelevisionEntity>()
        data.value = DataDummy.favoriteTelevisionDummyData()[0]
        val id = data.value!!.id
        cataTubeRepository.insertTelevisionFavorite(id)
    }


    @Test
    fun deleteTelevisionFavorite() = testCoroutineRule.runBlockingTest {
        val data = MutableLiveData<FavoriteTelevisionEntity>()
        data.value = DataDummy.favoriteTelevisionDummyData()[0]
        val id = data.value!!.id
        cataTubeRepository.deleteTelevisionFavorite(id)
    }
}
