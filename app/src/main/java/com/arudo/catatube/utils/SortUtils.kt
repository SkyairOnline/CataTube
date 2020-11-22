package com.arudo.catatube.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val newest = "Newest"
    const val oldest = "Oldest"

    fun getSortedQuery(filter: String, db: String, favoriteDb: String): SimpleSQLiteQuery {
        val filterQuery =
            StringBuilder().append("SELECT * From $db a join $favoriteDb b on a.id = b.id ")
        if (filter == newest) {
            filterQuery.append("Order by a.id desc")
        } else if (filter == oldest) {
            filterQuery.append("Order by a.id asc")
        }
        return SimpleSQLiteQuery(filterQuery.toString())
    }
}