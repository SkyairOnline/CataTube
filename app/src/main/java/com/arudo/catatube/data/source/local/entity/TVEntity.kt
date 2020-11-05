package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "televisionentity")
@Parcelize
data class TVEntity(

    @ColumnInfo(name = "firstAirDate")
    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String? = null,

    @ColumnInfo(name = "voteAverage")
    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @ColumnInfo(name = "name")
    @field:SerializedName("name")
    val name: String? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "posterPath")
    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "status")
    @field:SerializedName("status")
    val status: String? = null,

    @ColumnInfo(name = "type")
    @field:SerializedName("type")
    val type: String? = null,

    @ColumnInfo(name = "season")
    @field:SerializedName("number_of_seasons")
    val season: Int? = null,

    @ColumnInfo(name = "episode")
    @field:SerializedName("number_of_episodes")
    val episode: Int? = null,

    ) : Parcelable

