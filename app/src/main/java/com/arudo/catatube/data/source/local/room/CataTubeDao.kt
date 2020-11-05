package com.arudo.catatube.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem

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
}