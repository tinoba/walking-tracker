package com.tinoba.data.network.mapper

import com.tinoba.data.network.model.ApiPhotoSizes

class ApiMapperImpl : ApiMapper {

    override fun toPhotoUrl(apiPhotoSizes: ApiPhotoSizes): String {
        return apiPhotoSizes.sizes.size.getOrNull(8)?.url ?: ""
    }
}