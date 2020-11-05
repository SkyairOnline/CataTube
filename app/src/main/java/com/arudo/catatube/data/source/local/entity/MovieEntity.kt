package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntity(

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("runtime")
	val runtime: Double? = null,

	@field:SerializedName("tagline")
	val tagline: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("budget")
	val budget: Double? = null,

	@field:SerializedName("revenue")
	val revenue: Double? = null,

	) : Parcelable
