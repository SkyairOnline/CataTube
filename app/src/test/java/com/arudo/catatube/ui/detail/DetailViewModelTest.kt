package com.arudo.catatube.ui.detail

import com.arudo.catatube.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var detailViewModelMovie: DetailViewModel
    private lateinit var detailViewModelTelevision: DetailViewModel
    private val dummyDataMovie = DataDummy.movieDummyData()[0]
    private val movieId = dummyDataMovie.id
    private val dummyDataTelevision = DataDummy.televisionDummyData()[0]
    private val televisionId = dummyDataTelevision.id

    @Before
    fun setUp() {
        detailViewModelMovie = DetailViewModel()
        detailViewModelMovie.setDetailMovieTelevision(movieId!!)
        detailViewModelTelevision = DetailViewModel()
        detailViewModelMovie.setDetailMovieTelevision(televisionId!!)
    }

    @Test
    fun getDetailMovie() {
        dummyDataMovie.id?.let { detailViewModelMovie.setDetailMovieTelevision(it) }
        val movieEntity = detailViewModelMovie.getDetailMovieTelevision()
        assertNotNull(movieEntity)
        assertEquals(dummyDataMovie.id, movieEntity.id)
        assertEquals(dummyDataMovie.image, movieEntity.image)
        assertEquals(dummyDataMovie.title, movieEntity.title)
        assertEquals(dummyDataMovie.year, movieEntity.year)
        assertEquals(dummyDataMovie.genre, movieEntity.genre)
        assertEquals(dummyDataMovie.duration, movieEntity.duration)
        assertEquals(dummyDataMovie.rating, movieEntity.rating)
        assertEquals(dummyDataMovie.quote, movieEntity.quote)
        assertEquals(dummyDataMovie.overview, movieEntity.overview)
        assertEquals(dummyDataMovie.status, movieEntity.status)
        assertEquals(dummyDataMovie.language, movieEntity.language)
        assertEquals(dummyDataMovie.budget, movieEntity.budget)
        assertEquals(dummyDataMovie.revenue, movieEntity.revenue)
    }

    @Test
    fun getDetailTelevision() {
        dummyDataTelevision.id?.let { detailViewModelMovie.setDetailMovieTelevision(it) }
        val televisionEntity = detailViewModelMovie.getDetailMovieTelevision()
        assertNotNull(televisionEntity)
        assertEquals(dummyDataTelevision.id, televisionEntity.id)
        assertEquals(dummyDataTelevision.image, televisionEntity.image)
        assertEquals(dummyDataTelevision.title, televisionEntity.title)
        assertEquals(dummyDataTelevision.year, televisionEntity.year)
        assertEquals(dummyDataTelevision.genre, televisionEntity.genre)
        assertEquals(dummyDataTelevision.duration, televisionEntity.duration)
        assertEquals(dummyDataTelevision.rating, televisionEntity.rating)
        assertEquals(dummyDataTelevision.quote, televisionEntity.quote)
        assertEquals(dummyDataTelevision.overview, televisionEntity.overview)
        assertEquals(dummyDataTelevision.status, televisionEntity.status)
        assertEquals(dummyDataTelevision.language, televisionEntity.language)
        assertEquals(dummyDataTelevision.budget, televisionEntity.budget)
        assertEquals(dummyDataTelevision.revenue, televisionEntity.revenue)
    }

}