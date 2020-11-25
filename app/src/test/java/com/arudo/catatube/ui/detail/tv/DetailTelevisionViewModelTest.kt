package com.arudo.catatube.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.FavoriteTelevisionEntity
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.TestCoroutineRule
import com.arudo.catatube.vo.Resource
import junit.framework.TestCase
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
class DetailTelevisionViewModelTest {

    private lateinit var detailTelevisionViewModel: DetailTelevisionViewModel
    private val dummyDataTelevision = Resource.success(DataDummy.televisionDummyData()[0])
    private val televisionId = dummyDataTelevision.data?.id!!

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<Resource<TVEntity>>

    @Mock
    private lateinit var favoriteObserver: Observer<FavoriteTelevisionEntity>

    @Before
    fun setUp() {
        detailTelevisionViewModel =
            DetailTelevisionViewModel(cataTubeRepository, testCoroutineRule.testCoroutineDispatcher)
        detailTelevisionViewModel.setDetailTelevision(televisionId)
    }

    @Test
    fun testGetDetailTelevision() = testCoroutineRule.runBlockingTest {
        val television = MutableLiveData<Resource<TVEntity>>()
        television.value = dummyDataTelevision
        `when`(cataTubeRepository.getTelevisionData(televisionId)).thenReturn(television)
        detailTelevisionViewModel.getDetailTelevision().observeForever(observer)
        verify(observer).onChanged(dummyDataTelevision)
    }

    @Test
    fun testFavoriteTelevision() = testCoroutineRule.runBlockingTest {
        detailTelevisionViewModel.setFavoriteTelevision(televisionId)
        val dummyTelevisionData = MutableLiveData<FavoriteTelevisionEntity>()
        dummyTelevisionData.value = DataDummy.favoriteTelevisionDummyData()[0]
        `when`(cataTubeRepository.getFavoriteTelevision(televisionId)).thenReturn(
            dummyTelevisionData
        )
        val televisionData = detailTelevisionViewModel.getFavoriteTelevision(televisionId).value
        verify(cataTubeRepository).getFavoriteTelevision(televisionId)
        TestCase.assertNotNull(televisionData)
        TestCase.assertEquals(televisionId, televisionData?.id)
        detailTelevisionViewModel.getFavoriteTelevision(televisionId)
            .observeForever(favoriteObserver)
        verify(favoriteObserver).onChanged(DataDummy.favoriteTelevisionDummyData()[0])
        detailTelevisionViewModel.deleteFavoriteTelevision(televisionId)
    }
}