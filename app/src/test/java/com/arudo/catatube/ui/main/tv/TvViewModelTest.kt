package com.arudo.catatube.ui.main.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.TestCoroutineRule
import com.arudo.catatube.vo.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
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
class TvViewModelTest {

    private lateinit var tvViewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<TelevisionResultsItem>>>

    @Before
    fun setUp() {
        tvViewModel = TvViewModel(cataTubeRepository)
    }

    @Test
    fun testGetMovieList() = testCoroutineRule.runBlockingTest {
        val dummyTelevisionList = Resource.success(DataDummy.televisionListDummyData())
        val televisionList = MutableLiveData<Resource<List<TelevisionResultsItem>>>()
        televisionList.value = dummyTelevisionList
        `when`(cataTubeRepository.getTelevisionsList()).thenReturn(televisionList)
        val televisionListData = tvViewModel.getTelevisionsList().value
        verify(cataTubeRepository).getTelevisionsList()
        assertNotNull(televisionListData)
        assertEquals(1, televisionListData?.data?.size)
        tvViewModel.getTelevisionsList().observeForever(observer)
        verify(observer).onChanged(dummyTelevisionList)
    }

    @Test
    fun testGetNullMovieList() = testCoroutineRule.runBlockingTest {
        val dummyTelevisionList = null
        val televisionList = MutableLiveData<Resource<List<TelevisionResultsItem>>>()
        televisionList.value = dummyTelevisionList
        `when`(cataTubeRepository.getTelevisionsList()).thenReturn(televisionList)
        val televisionListData = tvViewModel.getTelevisionsList().value
        verify(cataTubeRepository).getTelevisionsList()
        assertNull(televisionListData)
        tvViewModel.getTelevisionsList().observeForever(observer)
        verify(observer).onChanged(dummyTelevisionList)
    }
}