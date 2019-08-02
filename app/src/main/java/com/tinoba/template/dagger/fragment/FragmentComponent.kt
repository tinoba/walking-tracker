package com.tinoba.template.dagger.fragment

import com.tinoba.template.dagger.activity.ActivityComponent
import com.tinoba.template.dagger.activity.ActivityComponentExposes
import com.tinoba.template.dagger.fragment.module.FragmentModule
import com.tinoba.template.dagger.fragment.module.FragmentPresenterModule
import dagger.Component

@FragmentScope
@Component(dependencies = [ActivityComponent::class],
        modules = [
        FragmentModule::class,
        FragmentPresenterModule::class
        ])
interface FragmentComponent : FragmentComponentInjects, FragmentComponentExposes, ActivityComponentExposes {

    object Initializer {

        fun init(fragment: DaggerFragment, activityComponent: ActivityComponent): FragmentComponent =
                DaggerFragmentComponent.builder()
                        .activityComponent(activityComponent)
                        .fragmentModule(FragmentModule(fragment))
                        .fragmentPresenterModule(FragmentPresenterModule(fragment))
                        .build()

    }
}
