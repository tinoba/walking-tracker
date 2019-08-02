package com.tinoba.template.util

import okhttp3.OkHttpClient

interface OkHttpClientBuilderUtils {

    fun setupTls(builder: OkHttpClient.Builder)
}
