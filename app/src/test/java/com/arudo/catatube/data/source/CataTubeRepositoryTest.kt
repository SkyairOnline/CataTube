package com.arudo.catatube.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arudo.catatube.utils.DataDummy
import com.arudo.catatube.utils.LiveDataTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CataTubeRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var cataTubeRepository: FakeCataTubeRepository

    @Before
    fun setUp() {
        cataTubeRepository = FakeCataTubeRepository()
    }

    @Test
    fun getMovieList() {
        val movieListEntity = DataDummy.movieListDummyData()
        val movieListResponse = LiveDataTest.getValue(cataTubeRepository.getMoviesList())
        assertNotNull(movieListResponse)
        assertEquals(movieListEntity.size, movieListResponse.size)
    }

    @Test
    fun getMovieData() {
        val movieDataEntity = DataDummy.movieDummyData()[0]
        val movieDataResponse =
            LiveDataTest.getValue(cataTubeRepository.getMovieData(movieDataEntity.id))
        assertNotNull(movieDataResponse)
        assertEquals(movieDataEntity.id, movieDataResponse.id)
    }

    @Test
    fun getTelevisionList() {
        val televisionListEntity = DataDummy.televisionListDummyData()
        val televisionListResponse = LiveDataTest.getValue(cataTubeRepository.getTelevisionsList())
        assertNotNull(televisionListResponse)
        assertEquals(televisionListEntity.size, televisionListResponse.size)
    }

    @Test
    fun getTelevisionData() {
        val televisionDataEntity = DataDummy.televisionDummyData()[0]
        val televisionDataResponse =
            LiveDataTest.getValue(cataTubeRepository.getTelevisionData(televisionDataEntity.id))
        assertNotNull(televisionDataResponse)
        assertEquals(televisionDataEntity.id, televisionDataResponse.id)
    }
}