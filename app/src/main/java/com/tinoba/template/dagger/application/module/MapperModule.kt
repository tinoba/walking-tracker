package com.tinoba.template.dagger.application.module

import com.tinoba.data.network.mapper.ApiMapper
import com.tinoba.data.network.mapper.ApiMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Provides
    @Singleton
    fun provideApiMapper(): ApiMapper = ApiMapperImpl()


    interface Exposes {

    }
}