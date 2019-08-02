package com.tinoba.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class DbImage(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "image_url")
    val url: String
) {

    companion object {

        val EMPTY = DbImage("")
    }
}