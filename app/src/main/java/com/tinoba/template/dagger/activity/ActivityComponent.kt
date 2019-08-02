package com.tinoba.template.dagger.activity

import com.tinoba.template.dagger.activity.module.ActivityModule
import com.tinoba.template.dagger.activity.module.ActivityPresenterModule
import com.tinoba.template.dagger.application.ApplicationComponent
import com.tinoba.template.dagger.application.ApplicationComponentExposes
import dagger.Component

@ActivityScope
@Component(dependencies = [(ApplicationComponent::class)],
        modules = [
        ActivityModule::class,
        ActivityPresenterModule::class
        ]
)
interface ActivityComponent : ActivityComponentInjects, ActivityComponentExposes, ApplicationComponentExposes {

    object Initializer {

        fun init(daggerActivity: DaggerActivity, applicationComponent: ApplicationComponent): ActivityComponent =
                DaggerActivityComponent.builder()
                        .applicationComponent(applicationComponent)
                        .activityModule(ActivityModule(daggerActivity))
                        .activityPresenterModule(ActivityPresenterModule(daggerActivity))
                        .build()

    }
}
