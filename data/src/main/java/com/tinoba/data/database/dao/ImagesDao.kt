package com.tinoba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinoba.data.database.model.DbImage
import io.reactivex.Flowable

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveImageUrl(dbImage: DbImage)

    @Query("SELECT * FROM image ORDER BY image_url DESC")
    fun getImageUrls(): Flowable<List<DbImage>>

    @Query("DELETE FROM image")
    fun clearImageUrls()
}