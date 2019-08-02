package com.tinoba.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiPhotoId(@SerializedName("photos") var photos: Photos) {

    data class Photos(@SerializedName("photo") var photo: List<Photo>) {

        data class Photo(@SerializedName("id") var id: String)
    }
}