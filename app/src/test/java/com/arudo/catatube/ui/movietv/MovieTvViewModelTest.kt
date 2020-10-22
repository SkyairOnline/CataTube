package com.arudo.catatube.ui.movietv

import com.arudo.catatube.data.MovieTVEntity
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MovieTvViewModelTest : TestCase() {

    private lateinit var movieTvViewModel: MovieTvViewModel

    @Before
    override fun setUp() {
        movieTvViewModel = MovieTvViewModel()
    }

    @Test
    fun testGetMovies() {
        val movieData = movieTvViewModel.getMovies()
        assertNotNull(movieData)
        assertEquals(19, movieData.size)
    }

    @Test
    fun testGetTelevisions() {
        val televisionData = movieTvViewModel.getTelevisions()
        assertNotNull(televisionData)
        assertEquals(20, televisionData.size)
    }

    @Test
    fun testZeroData() {
        val movieData = ArrayList<MovieTVEntity>()
        assertNotNull(movieData)
        assertEquals(0, movieData.size)
    }

    @Test
    fun testNullData() {
        val movieData = null
        assertNull(movieData)
    }
}