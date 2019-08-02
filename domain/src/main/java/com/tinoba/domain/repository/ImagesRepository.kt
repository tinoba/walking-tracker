package com.tinoba.domain.repository

import com.tinoba.domain.model.Image
import com.tinoba.domain.model.PhotoLocation
import io.reactivex.Completable
import io.reactivex.Flowable

interface ImagesRepository {

    fun fetchAndStorePhoto(photoLocation: PhotoLocation): Completable

    fun fotoUrls(): Flowable<List<Image>>

    fun clearUrls(): Completable
}