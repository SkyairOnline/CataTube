package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieTVEntity(
    var id: Int? = null,
    var image: String? = null,
    var title: String? = null,
    var date: String? = null,
    var duration: Double? = null,
    var rating: Double? = null,
    var quote: String? = null,
    var overview: String? = null,
    var status: String? = null,
    var type: String? = null
) : Parcelable