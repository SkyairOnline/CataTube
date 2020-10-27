package com.arudo.catatube.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieTVResponse(
    var id: Int? = null,
    var image: String? = null,
    var title: String? = null,
    var year: String? = null,
    var genre: String? = null,
    var duration: Double? = null,
    var rating: Double? = null,
    var quote: String? = null,
    var overview: String? = null,
    var status: String? = null,
    var language: String? = null,
    var budget: Double? = null,
    var revenue: Double? = null,
    var type: String? = null
) : Parcelable