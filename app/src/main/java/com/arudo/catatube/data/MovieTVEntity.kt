package com.arudo.catatube.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieTVEntity(
        var id: String? = null,
        var image: Int? = null,
        var title: String? = null,
        var year: String? = null,
        var genre: String? = null,
        var duration: String? = null,
        var rating: String? = null,
        var quote: String? = null,
        var overview: String? = null,
        var status: String? = null,
        var language: String? = null,
        var budget: String? = null,
        var revenue: String? = null,
) : Parcelable