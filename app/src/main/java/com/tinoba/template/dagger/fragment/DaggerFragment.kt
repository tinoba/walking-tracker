package com.tinoba.template.dagger.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tinoba.template.dagger.ComponentFactory
import com.tinoba.template.dagger.activity.DaggerActivity

abstract class DaggerFragment : Fragment() {

    private var fragmentComponent: FragmentComponent? = null

    fun getDaggerActivity() = activity as DaggerActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getFragmentComponent())
    }

    protected abstract fun inject(fragmentComponent: FragmentComponent)

    fun getFragmentComponent(): FragmentComponent {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(this, getDaggerActivity().getActivityComponent())
        }

        return fragmentComponent as FragmentComponent
    }
}
