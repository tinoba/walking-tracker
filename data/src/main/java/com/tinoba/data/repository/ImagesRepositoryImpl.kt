package com.tinoba.data.repository

import com.tinoba.data.database.dao.crudder.ImagesCrudder
import com.tinoba.data.network.service.FlickrClient
import com.tinoba.domain.model.PhotoLocation
import com.tinoba.domain.repository.ImagesRepository

class ImagesRepositoryImpl(
    private val flickrClient: FlickrClient,
    private val imagesCrudder: ImagesCrudder
) : ImagesRepository {

    override fun fetchAndStorePhoto(photoLocation: PhotoLocation) =
        flickrClient.getPhotoId(photoLocation)
            .map { it.photos.photo.first().id }
            .flatMap { flickrClient.getPhotoUrl(it) }
            .flatMapCompletable { imagesCrudder.saveImageUrl(it) }

    override fun fotoUrls() = imagesCrudder.getImagesUrl()

    override fun clearUrls() = imagesCrudder.clearUrls()
}