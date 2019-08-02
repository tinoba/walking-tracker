package com.tinoba.data.network.model

import com.google.gson.annotations.SerializedName

class ApiNewsResponse {

    @SerializedName("articles")
    lateinit var articles: List<ApiArticle?>
}
