package com.tinoba.data.database.dao.crudder

import com.tinoba.domain.model.Image
import io.reactivex.Completable
import io.reactivex.Flowable

interface ImagesCrudder {

    fun saveImageUrl(url: String) : Completable

    fun getImagesUrl(): Flowable<List<Image>>

    fun clearUrls() : Completable
}