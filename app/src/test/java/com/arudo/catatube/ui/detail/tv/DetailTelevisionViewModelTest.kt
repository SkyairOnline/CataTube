package com.arudo.catatube.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arudo.catatube.data.source.CataTubeRepository
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.utils.DataDummy
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

@RunWith(MockitoJUnitRunner::class)
class DetailTelevisionViewModelTest {

    private lateinit var detailTelevisionViewModel: DetailTelevisionViewModel
    private val dummyDataTelevision = DataDummy.televisionDummyData()[0]
    private val televisionId = dummyDataTelevision.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var cataTubeRepository: CataTubeRepository

    @Mock
    private lateinit var observer: Observer<TVEntity>

    @Before
    fun setUp() {
        detailTelevisionViewModel = DetailTelevisionViewModel(cataTubeRepository)
        detailTelevisionViewModel.setDetailTelevision(televisionId)
    }

    @Test
    fun testGetDetailMovie() {
        val television = MutableLiveData<TVEntity>()
        television.value = dummyDataTelevision
        `when`(cataTubeRepository.getTelevisionData(televisionId)).thenReturn(television)
        val televisionData = detailTelevisionViewModel.getDetailTelevision().value
        verify(cataTubeRepository).getTelevisionData(televisionId)
        assertNotNull(televisionData)
        assertEquals(dummyDataTelevision.firstAirDate, televisionData?.firstAirDate)
        assertEquals(dummyDataTelevision.overview, televisionData?.overview)
        assertEquals(dummyDataTelevision.voteAverage, televisionData?.voteAverage)
        assertEquals(dummyDataTelevision.name, televisionData?.name)
        assertEquals(dummyDataTelevision.episodeRunTime, televisionData?.episodeRunTime)
        assertEquals(dummyDataTelevision.id, televisionData?.id)
        assertEquals(dummyDataTelevision.posterPath, televisionData?.posterPath)
        assertEquals(dummyDataTelevision.status, televisionData?.status)
        assertEquals(dummyDataTelevision.type, televisionData?.type)
        assertEquals(dummyDataTelevision.season, televisionData?.season)
        assertEquals(dummyDataTelevision.episode, televisionData?.episode)
        detailTelevisionViewModel.getDetailTelevision().observeForever(observer)
        verify(observer).onChanged(dummyDataTelevision)
    }
}