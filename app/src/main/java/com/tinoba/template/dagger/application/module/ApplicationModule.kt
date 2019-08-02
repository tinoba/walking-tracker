package com.tinoba.template.dagger.application.module

import android.content.Context
import com.tinoba.template.appliaction.WalkingTrackerApplication
import com.tinoba.template.dagger.application.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val walkingTrackerApplication: WalkingTrackerApplication) {

    @Provides
    @Singleton
    internal fun provideMercuryApplication(): WalkingTrackerApplication {
        return walkingTrackerApplication
    }

    @Provides
    @Singleton
    @ForApplication
    internal fun provideContext(): Context {
        return walkingTrackerApplication
    }

    interface Exposes {

        fun mercuryApplication(): WalkingTrackerApplication

        @ForApplication
        fun context(): Context
    }
}
