package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListEntity(

    @field:SerializedName("results")
    val results: ArrayList<MovieResultsItem>

) : Parcelable

@Parcelize
data class MovieResultsItem(

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    ) : Parcelable
