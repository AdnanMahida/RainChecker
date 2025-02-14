package com.ad.composemvvmstarter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ad.composemvvmstarter.data.local.entity.Demo
import com.ad.composemvvmstarter.database.converters.CoreConverters
import com.ad.composemvvmstarter.database.dao.DemoDao
import com.ad.composemvvmstarter.utility.AppConstants


@Database(
    entities = [
        Demo::class
    ],
    version = AppConstants.DB_VERSION,
    exportSchema = false
)
@TypeConverters(CoreConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDemoDao(): DemoDao
}
