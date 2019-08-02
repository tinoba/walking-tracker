package com.tinoba.template.dagger.application

import com.tinoba.template.dagger.application.module.*

interface ApplicationComponentExposes : ApplicationModule.Exposes,
        ThreadingModule.Exposes,
        UseCaseModule.Exposes,
        DataModule.Exposes,
        MapperModule.Exposes,
        UtilsModule.Exposes
