package com.tinoba.template.dagger.activity.module

import com.tinoba.template.dagger.activity.ActivityScope
import com.tinoba.template.dagger.activity.DaggerActivity
import com.tinoba.template.ui.home.HomeContract
import com.tinoba.template.ui.home.HomePresenter

import dagger.Module
import dagger.Provides

@Module
class ActivityPresenterModule(private val daggerActivity: DaggerActivity) {

    @Provides
    @ActivityScope
    internal fun provideHomePresenter(): HomeContract.Presenter {
        val presenter = HomePresenter(daggerActivity as HomeContract.View)
        daggerActivity.getActivityComponent().inject(presenter)
        return presenter
    }
}
