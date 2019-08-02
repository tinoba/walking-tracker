package com.tinoba.template.dagger.application.module

import com.tinoba.domain.interactor.ClearImageUrlsUseCase
import com.tinoba.domain.interactor.GetImageUrlsUseCase
import com.tinoba.domain.interactor.SaveImageUrlUseCase
import com.tinoba.domain.repository.ImagesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideSaveImageUrlUseCase(imagesRepository: ImagesRepository): SaveImageUrlUseCase =
        SaveImageUrlUseCase(imagesRepository)

    @Provides
    @Singleton
    fun provideGetImageUrlsUseCase(imagesRepository: ImagesRepository): GetImageUrlsUseCase =
        GetImageUrlsUseCase(imagesRepository)

    @Provides
    @Singleton
    fun provideClearImageUrlsUseCase(imagesRepository: ImagesRepository): ClearImageUrlsUseCase =
        ClearImageUrlsUseCase(imagesRepository)

    interface Exposes {
        fun saveImageUrlUseCase(): SaveImageUrlUseCase
        fun getImageUrlsUseCase(): GetImageUrlsUseCase
        fun clearImageUrlsUseCase(): ClearImageUrlsUseCase
    }
}
