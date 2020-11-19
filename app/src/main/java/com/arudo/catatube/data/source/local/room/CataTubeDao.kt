package com.arudo.catatube.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arudo.catatube.data.source.local.entity.*

@Dao
interface CataTubeDao {
    @Query("SELECT * FROM movielistentity")
    fun getMovieList(): LiveData<List<MovieResultsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movieList: List<MovieResultsItem>)

    @Query("SELECT * FROM televisionlistentity")
    fun getTelevisionList(): LiveData<List<TelevisionResultsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTelevisionList(televisionList: List<TelevisionResultsItem>)

    @Query("SELECT * FROM movieentity where id = :id")
    fun getMovie(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM televisionentity where id = :id")
    fun getTelevision(id: Int): LiveData<TVEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTelevision(television: TVEntity)

    @Query("SELECT * FROM movieentity a join favoritemovieentity b on a.id = b.id")
    fun getMovieFavoriteList(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM favoritemovieentity where id = :id")
    fun getMovieFavorite(id: Int): LiveData<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Query("SELECT * FROM televisionentity a join favoritetelevisionentity b on a.id = b.id")
    fun getTelevisionFavoriteList(): LiveData<List<TVEntity>>

    @Query("SELECT * FROM favoritetelevisionentity where id = :id")
    fun getTelevisionFavorite(id: Int): LiveData<FavoriteTelevisionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritTelevision(favoriteTelevision: FavoriteTelevisionEntity)

    @Delete
    suspend fun deleteFavoriteTelevision(favoriteTelevision: FavoriteTelevisionEntity)
}