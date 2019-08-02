package com.tinoba.template.dagger.application.module

import android.content.Context
import android.os.Build
import androidx.room.Room
import com.google.gson.Gson
import com.tinoba.data.database.WalkingTrackerDatabase
import com.tinoba.data.database.dao.ImagesDao
import com.tinoba.data.database.dao.crudder.ImagesCrudder
import com.tinoba.data.database.dao.crudder.ImagesCrudderImpl
import com.tinoba.data.network.mapper.ApiMapper
import com.tinoba.data.network.service.FlickrClient
import com.tinoba.data.network.service.FlickrClientImpl
import com.tinoba.data.network.service.FlickrService
import com.tinoba.data.repository.ImagesRepositoryImpl
import com.tinoba.domain.repository.ImagesRepository
import com.tinoba.template.BuildConfig
import com.tinoba.template.dagger.application.ForApplication
import com.tinoba.template.util.OkHttpClientBuilderUtils
import com.tinoba.template.util.OkHttpClientBuilderUtilsImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClientBuilderUtils(): OkHttpClientBuilderUtils {
        return OkHttpClientBuilderUtilsImpl()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(interceptor: HttpLoggingInterceptor, okHttpClientBuilderUtils: OkHttpClientBuilderUtils): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.interceptors().add(interceptor)
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            okHttpClientBuilderUtils.setupTls(builder)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideTemplateService(retrofit: Retrofit): FlickrService = retrofit.create(FlickrService::class.java)

    @Provides
    @Singleton
    internal fun provideTemplateClient(flickrService: FlickrService, apiMapper: ApiMapper): FlickrClient =
        FlickrClientImpl(flickrService, apiMapper)

    @Provides
    @Singleton
    internal fun provideNewsRepository(
        flickrClient: FlickrClient,
        imagesCrudder: ImagesCrudder
    ): ImagesRepository = ImagesRepositoryImpl(flickrClient, imagesCrudder)

    @Provides
    @Singleton
    fun provideWalkingTrackerDatabase(@ForApplication context: Context): WalkingTrackerDatabase =
        Room.databaseBuilder(context, WalkingTrackerDatabase::class.java, WalkingTrackerDatabase.NAME)
            .build()

    @Provides
    @Singleton
    fun provideImagesDao(database: WalkingTrackerDatabase): ImagesDao = database.imageUrls()

    @Provides
    @Singleton
    fun provideImagesCrudder(imagesDao: ImagesDao): ImagesCrudder =
        ImagesCrudderImpl(imagesDao)

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.flickr.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    interface Exposes {

    }

}
