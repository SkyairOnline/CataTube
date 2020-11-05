package com.arudo.catatube.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arudo.catatube.data.source.local.entity.MovieEntity
import com.arudo.catatube.data.source.local.entity.MovieResultsItem
import com.arudo.catatube.data.source.local.entity.TVEntity
import com.arudo.catatube.data.source.local.entity.TelevisionResultsItem

@Database(
    entities = [MovieResultsItem::class, TelevisionResultsItem::class, MovieEntity::class, TVEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CataTubeDatabase : RoomDatabase() {
    abstract fun cataTubeDao(): CataTubeDao

    companion object {
        @Volatile
        private var cataTubeDatabase: CataTubeDatabase? = null

        fun getInstance(context: Context): CataTubeDatabase {
            return cataTubeDatabase ?: synchronized(this) {
                cataTubeDatabase ?: Room
                    .databaseBuilder(
                        context.applicationContext,
                        CataTubeDatabase::class.java,
                        "CataTube.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
