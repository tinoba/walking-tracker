package com.tinoba.template.dagger.application.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class ThreadingModule {

    companion object {

        const val MAIN_SCHEDULER: String = "main_scheduler"

        const val BACKGROUND_SCHEDULER: String = "background_scheduler"
    }

    @Provides
    @Singleton
    @Named(MAIN_SCHEDULER)
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(BACKGROUND_SCHEDULER)
    fun provideBackgroundScheduler(): Scheduler = Schedulers.io()

    interface Exposes {

        @Named(ThreadingModule.MAIN_SCHEDULER)
        fun mainScheduler(): Scheduler

        @Named(ThreadingModule.BACKGROUND_SCHEDULER)
        fun backgroundScheduler(): Scheduler
    }
}
