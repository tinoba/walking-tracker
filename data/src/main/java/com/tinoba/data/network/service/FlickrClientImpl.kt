package com.tinoba.data.network.service

import com.tinoba.data.network.mapper.ApiMapper
import com.tinoba.data.network.model.ApiPhotoId
import com.tinoba.domain.model.PhotoLocation
import io.reactivex.Single

class FlickrClientImpl(
    private val flickrService: FlickrService,
    private val apiMapper: ApiMapper
) : FlickrClient {

    companion object {
        const val API_KEY = "a2718a9539e271bb9866d0f098f971f4"
        const val ONE_PAGE = 1
        const val JSON_FORMAT = "json"
        const val CALLBACK_VALUE = 1
        const val OUTDOORS_CONTEXT = 2
    }

    override fun getPhotoId(photoLocation: PhotoLocation): Single<ApiPhotoId> = with(photoLocation) {
        return Single.defer {
            flickrService.getPhotoId(
                API_KEY,
                latitude,
                longitude,
                ONE_PAGE,
                OUTDOORS_CONTEXT,
                JSON_FORMAT,
                CALLBACK_VALUE
            )
        }
    }

    override fun getPhotoUrl(photoId: String) =
        Single.defer {
            flickrService.getPhotoUrl(API_KEY, photoId, JSON_FORMAT, CALLBACK_VALUE)
                .map(apiMapper::toPhotoUrl)
        }
}