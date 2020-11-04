package com.arudo.catatube.ui.main.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem
import com.arudo.catatube.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var tvViewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<TelevisionResultsItem>>

    @Before
    fun setUp() {
        tvViewModel = TvViewModel(cataTubeRepository)
    }

    @Test
    fun testGetMovieList() {
        val dummyTelevisionList = DataDummy.televisionListDummyData()
        val televisionList = MutableLiveData<ArrayList<TelevisionResultsItem>>()
        televisionList.value = dummyTelevisionList
        `when`(cataTubeRepository.getTelevisionsList()).thenReturn(televisionList)
        val televisionListData = tvViewModel.getTelevisionsList().value
        Mockito.verify(cataTubeRepository).getTelevisionsList()
        assertNotNull(televisionListData)
        assertEquals(20, televisionListData?.size)
        tvViewModel.getTelevisionsList().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTelevisionList)
    }

    @Test
    fun testGetZeroMovieList() {
        val dummyTelevisionList = ArrayList<TelevisionResultsItem>()
        val televisionList = MutableLiveData<ArrayList<TelevisionResultsItem>>()
        televisionList.value = dummyTelevisionList
        `when`(cataTubeRepository.getTelevisionsList()).thenReturn(televisionList)
        val televisionListData = tvViewModel.getTelevisionsList().value
        Mockito.verify(cataTubeRepository).getTelevisionsList()
        assertNotNull(televisionListData)
        assertEquals(0, televisionListData?.size)
        tvViewModel.getTelevisionsList().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTelevisionList)
    }

    @Test
    fun testGetNullMovieList() {
        val dummyTelevisionList = null
        val televisionList = MutableLiveData<ArrayList<TelevisionResultsItem>>()
        televisionList.value = dummyTelevisionList
        `when`(cataTubeRepository.getTelevisionsList()).thenReturn(televisionList)
        val televisionListData = tvViewModel.getTelevisionsList().value
        Mockito.verify(cataTubeRepository).getTelevisionsList()
        assertNull(televisionListData)
        tvViewModel.getTelevisionsList().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTelevisionList)
    }
}