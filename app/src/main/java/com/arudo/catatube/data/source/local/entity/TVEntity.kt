package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVEntity(

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Double>,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("number_of_seasons")
    val season: Int? = null,

    @field:SerializedName("number_of_episodes")
    val episode: Int? = null,

    ) : Parcelable

