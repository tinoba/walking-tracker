package com.tinoba.data.database.dao.crudder

import com.tinoba.data.database.dao.ImagesDao
import com.tinoba.data.database.model.DbImage
import com.tinoba.domain.model.Image
import io.reactivex.Completable

class ImagesCrudderImpl(private val imagesDao: ImagesDao) : ImagesCrudder {

    override fun clearUrls() =
        Completable.fromAction { imagesDao.clearImageUrls() }

    override fun saveImageUrl(url: String) =
        Completable.fromAction { imagesDao.saveImageUrl(DbImage(url)) }

    override fun getImagesUrl() = imagesDao.getImageUrls()
        .map { it.map { Image(it.url) } }
}