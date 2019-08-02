package com.tinoba.template.dagger.fragment.module

import com.tinoba.template.dagger.fragment.DaggerFragment
import com.tinoba.template.dagger.fragment.FragmentComponent
import dagger.Module

@Module
class FragmentPresenterModule(private val daggerFragment: DaggerFragment) {

    private val fragmentComponent: FragmentComponent
        get() = daggerFragment.getFragmentComponent()
}
