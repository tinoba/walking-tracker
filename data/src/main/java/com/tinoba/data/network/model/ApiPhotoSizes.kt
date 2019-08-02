package com.tinoba.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiPhotoSizes(@SerializedName("sizes") val sizes: Sizes) {

    data class Sizes(@SerializedName("size") val size: List<Size>) {

        data class Size(@SerializedName("source") val url: String)
    }
}