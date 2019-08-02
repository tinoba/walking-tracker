package com.tinoba.template.dagger.activity.module

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import com.tinoba.template.dagger.activity.ActivityScope
import com.tinoba.template.dagger.activity.DaggerActivity
import com.tinoba.template.dagger.activity.ForActivity
import com.tinoba.template.router.Router
import com.tinoba.template.router.RouterImpl
import com.tinoba.template.ui.home.LocationImageAdapter


import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val daggerActivity: DaggerActivity) {

    @Provides
    @ActivityScope
    @ForActivity
    internal fun provideActivityContext(): Context {
        return daggerActivity
    }

    @Provides
    @ActivityScope
    internal fun provideActivity(): Activity {
        return daggerActivity
    }

    @Provides
    @ActivityScope
    internal fun provideFragmentManager(): FragmentManager {
        return daggerActivity.supportFragmentManager
    }

    @Provides
    @ActivityScope
    internal fun provideLayoutInflater(@ForActivity context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }


    @Provides
    @ActivityScope
    internal fun provideRouter(fragmentManager: FragmentManager): Router {
        return RouterImpl(daggerActivity, fragmentManager)
    }

    @Provides
    @ActivityScope
    internal fun provideNewsListAdapter(layoutInflater: LayoutInflater, @ForActivity context: Context): LocationImageAdapter {
        return LocationImageAdapter(layoutInflater, context)
    }

    interface Exposes {

        fun activity(): Activity

        @ForActivity
        fun context(): Context

        fun fragmentManager(): FragmentManager

        fun layoutInflater(): LayoutInflater

        fun router(): Router
    }
}
