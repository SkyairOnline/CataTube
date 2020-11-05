package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVListEntity(

    @field:SerializedName("results")
    val results: ArrayList<TelevisionResultsItem>

) : Parcelable

@Parcelize
data class TelevisionResultsItem(

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    ) : Parcelable
