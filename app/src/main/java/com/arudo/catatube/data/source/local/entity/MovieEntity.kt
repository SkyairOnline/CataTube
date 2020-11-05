package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movieentity")
@Parcelize
data class MovieEntity(

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String? = null,

    @ColumnInfo(name = "releaseDate")
    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "voteAverage")
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @ColumnInfo(name = "runtime")
    @field:SerializedName("runtime")
    val runtime: Double? = null,

    @ColumnInfo(name = "tagline")
    @field:SerializedName("tagline")
    val tagline: String? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "title")
    @field:SerializedName("title")
    val title: String? = null,

    @ColumnInfo(name = "status")
    @field:SerializedName("status")
    val status: String? = null,

    @ColumnInfo(name = "posterPath")
    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "budget")
    @field:SerializedName("budget")
    val budget: Double? = null,

    @ColumnInfo(name = "revenue")
    @field:SerializedName("revenue")
    val revenue: Double? = null,

    ) : Parcelable
