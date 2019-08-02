package com.tinoba.data.network.model

import com.google.gson.annotations.SerializedName

class ApiArticle {

    @SerializedName("author")
    var author = ""

    @SerializedName("title")
    var title = ""

    @SerializedName("description")
    var description = ""

    @SerializedName("urlToImage")
    var urlToImage = ""
}