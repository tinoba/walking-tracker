package com.tinoba.data.network.service

import com.tinoba.data.network.model.ApiPhotoId
import com.tinoba.data.network.model.ApiPhotoSizes
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {

    companion object {
        const val API_KEY = "api_key"
        const val LATITUDE = "lat"
        const val LONGITUDE = "lon"
        const val PHOTO_ID = "photo_id"
        const val FORMAT = "format"
        const val PER_PAGE = "per_page"
        const val GEO_CONTEXT = "geo_context"
        const val NO_JSON_CALLBACK = "nojsoncallback"
    }

    @GET("services/rest/?method=flickr.photos.search")
    fun getPhotoId(
        @Query(API_KEY) apiKey: String,
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double,
        @Query(PER_PAGE) perPage: Int,
        @Query(GEO_CONTEXT) geoContext: Int,
        @Query(FORMAT) format: String,
        @Query(NO_JSON_CALLBACK) noJsonCallback: Int
    ): Single<ApiPhotoId>

    @GET("services/rest/?method=flickr.photos.getSizes")
    fun getPhotoUrl(
        @Query(API_KEY) apiKey: String,
        @Query(PHOTO_ID) photoId: String,
        @Query(FORMAT) format: String,
        @Query(NO_JSON_CALLBACK) noJsonCallback: Int
    ): Single<ApiPhotoSizes>
}