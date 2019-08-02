package com.tinoba.data.network.mapper

import com.tinoba.data.network.model.ApiPhotoSizes

interface ApiMapper {

    fun toPhotoUrl(apiPhotoSizes: ApiPhotoSizes): String
}