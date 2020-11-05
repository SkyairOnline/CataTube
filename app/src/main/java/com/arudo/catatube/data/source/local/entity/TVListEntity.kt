package com.arudo.catatube.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVListEntity(

    @field:SerializedName("results")
    val results: ArrayList<TelevisionResultsItem>

) : Parcelable

@Entity(tableName = "televisionlistentity")
@Parcelize
data class TelevisionResultsItem(

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String? = null,

    @ColumnInfo(name = "posterPath")
    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "name")
    @field:SerializedName("name")
    val name: String? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    ) : Parcelable
