package com.arudo.catatube.ui.favorite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.SortUtils
import com.arudo.catatube.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
class TvFavoriteViewModelTest {

    private lateinit var televisionFavoriteViewModel: TvFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TVEntity>>

    @Mock
    private lateinit var televisionPagedList: PagedList<TVEntity>

    @Before
    fun setUp() {
        televisionFavoriteViewModel =
            TvFavoriteViewModel(cataTubeRepository, testCoroutineRule.testCoroutineDispatcher)
    }

    @Test
    fun testFavoriteListMovieNewest() = testCoroutineRule.runBlockingTest {
        val dummyTelevisionFavoriteList = televisionPagedList
        `when`(dummyTelevisionFavoriteList.size).thenReturn(3)
        val televisionFavoriteList = MutableLiveData<PagedList<TVEntity>>()
        televisionFavoriteList.value = dummyTelevisionFavoriteList
        `when`(cataTubeRepository.getFavoriteTelevisionListNewest()).thenReturn(
            televisionFavoriteList
        )
        val televisionFavoriteListEntity =
            televisionFavoriteViewModel.getFavoriteTelevisionList(SortUtils.newest).value
        verify(cataTubeRepository).getFavoriteTelevisionListNewest()
        assertNotNull(televisionFavoriteListEntity)
        assertEquals(3, televisionFavoriteListEntity?.size)
        televisionFavoriteViewModel.getFavoriteTelevisionList(SortUtils.newest)
            .observeForever(observer)
        verify(observer).onChanged(dummyTelevisionFavoriteList)
        televisionFavoriteViewModel.deleteFavoriteTelevision(DataDummy.televisionDummyData()[0])
    }

    @Test
    fun testFavoriteListMovieOldest() = testCoroutineRule.runBlockingTest {
        val dummyTelevisionFavoriteList = televisionPagedList
        `when`(dummyTelevisionFavoriteList.size).thenReturn(3)
        val televisionFavoriteList = MutableLiveData<PagedList<TVEntity>>()
        televisionFavoriteList.value = dummyTelevisionFavoriteList
        `when`(cataTubeRepository.getFavoriteTelevisionListOldest()).thenReturn(
            televisionFavoriteList
        )
        val televisionFavoriteListEntity =
            televisionFavoriteViewModel.getFavoriteTelevisionList(SortUtils.oldest).value
        verify(cataTubeRepository).getFavoriteTelevisionListOldest()
        assertNotNull(televisionFavoriteListEntity)
        assertEquals(3, televisionFavoriteListEntity?.size)
        televisionFavoriteViewModel.getFavoriteTelevisionList(SortUtils.oldest)
            .observeForever(observer)
        verify(observer).onChanged(dummyTelevisionFavoriteList)
        televisionFavoriteViewModel.deleteFavoriteTelevision(DataDummy.televisionDummyData()[0])
    }
}