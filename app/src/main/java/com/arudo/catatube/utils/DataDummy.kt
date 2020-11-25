package com.arudo.catatube.utils

import com.arudo.catatube.data.source.local.entity.*

object DataDummy {
    fun movieListDummyData(): List<MovieResultsItem> {
        val movies = ArrayList<MovieResultsItem>()
        movies.add(
            MovieResultsItem(
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "A Star is Born",
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                332562,
            )
        )
        movies.add(
            MovieResultsItem(
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Alita : Battle Angel",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                399579,
            )
        )
        return movies
    }

    fun movieDummyData(): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()
        movie.add(
            MovieEntity(
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "2018-10-03",
                2.0,
                136.0,
                "",
                332562,
                "A Star is Born",
                "Released",
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg",
                36000000.0,
                433888866.0
            )
        )
        return movie
    }

    fun favoriteMovieDummyData(): List<FavoriteMovieEntity> {
        val favoriteMovie = ArrayList<FavoriteMovieEntity>()
        favoriteMovie.add(
            FavoriteMovieEntity(
                332562
            )
        )
        return favoriteMovie
    }

    fun televisionListDummyData(): List<TelevisionResultsItem> {
        val televisions = ArrayList<TelevisionResultsItem>()
        televisions.add(
            TelevisionResultsItem(
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Arrow",
                1412,
            )
        )
        return televisions
    }

    fun televisionDummyData(): List<TVEntity> {
        val television = ArrayList<TVEntity>()
        television.add(
            TVEntity(
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                6.5,
                "Arrow",
                1412,
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Ended",
                "Scripted",
                8,
                170
            )
        )
        return television
    }

    fun favoriteTelevisionDummyData(): List<FavoriteTelevisionEntity> {
        val favoriteTelevision = ArrayList<FavoriteTelevisionEntity>()
        favoriteTelevision.add(
            FavoriteTelevisionEntity(
                1412
            )
        )
        return favoriteTelevision
    }
}