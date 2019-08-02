package com.tinoba.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tinoba.data.database.dao.ImagesDao
import com.tinoba.data.database.model.DbImage

@Database(entities = [DbImage::class], version = WalkingTrackerDatabase.VERSION)
abstract class WalkingTrackerDatabase : RoomDatabase() {

    companion object {

        const val VERSION = 1
        const val NAME = "WalkingTrackerDatabase"
    }

    abstract fun imageUrls(): ImagesDao
}