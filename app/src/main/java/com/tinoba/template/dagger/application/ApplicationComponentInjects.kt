package com.tinoba.template.dagger.application

import com.tinoba.template.appliaction.WalkingTrackerApplication
import com.tinoba.template.service.LocationService

interface ApplicationComponentInjects {

    fun inject(application: WalkingTrackerApplication)

    fun inject(locationService: LocationService)
}
