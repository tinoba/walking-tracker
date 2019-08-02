package com.tinoba.template.dagger

import com.tinoba.template.appliaction.WalkingTrackerApplication
import com.tinoba.template.dagger.activity.ActivityComponent
import com.tinoba.template.dagger.activity.DaggerActivity
import com.tinoba.template.dagger.application.ApplicationComponent
import com.tinoba.template.dagger.fragment.DaggerFragment
import com.tinoba.template.dagger.fragment.FragmentComponent


object ComponentFactory {

    fun createApplicationComponent(walkingTrackerApplication: WalkingTrackerApplication): ApplicationComponent {
        return ApplicationComponent.Initializer.init(walkingTrackerApplication)
    }

    fun createActivityComponent(daggerActivity: DaggerActivity, applicationComponent: ApplicationComponent): ActivityComponent {
        return ActivityComponent.Initializer.init(daggerActivity, applicationComponent)
    }

    fun createFragmentComponent(daggerFragment: DaggerFragment, activityComponent: ActivityComponent): FragmentComponent {
        return FragmentComponent.Initializer.init(daggerFragment, activityComponent)
    }
}