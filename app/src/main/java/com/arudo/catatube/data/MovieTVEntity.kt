package com.arudo.catatube.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieTVEntity(
    var id: String? = null,
    var image: Int? = null,
    var title: String? = null
) : Parcelable