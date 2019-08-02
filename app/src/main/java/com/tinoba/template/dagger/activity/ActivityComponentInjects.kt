package com.tinoba.template.dagger.activity

import com.tinoba.template.ui.home.HomeActivity
import com.tinoba.template.ui.home.HomePresenter

interface ActivityComponentInjects {

    fun inject(activity: HomeActivity)

    fun inject(presenter: HomePresenter)
}
