package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListEntity(

    @field:SerializedName("results")
    val results: ArrayList<MovieResultsItem>

) : Parcelable

@Entity(tableName = "movielistentity")
@Parcelize
data class MovieResultsItem(

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String? = null,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String? = null,

    @ColumnInfo(name = "posterPath")
    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    ) : Parcelable
