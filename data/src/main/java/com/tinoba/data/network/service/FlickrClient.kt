package com.tinoba.data.network.service

import com.tinoba.data.network.model.ApiPhotoId
import com.tinoba.domain.model.PhotoLocation
import io.reactivex.Single

interface FlickrClient {

    fun getPhotoId(photoLocation: PhotoLocation): Single<ApiPhotoId>

    fun getPhotoUrl(photoId: String): Single<String>
}