package com.tinoba.template.base

import android.os.Bundle
import com.tinoba.template.dagger.fragment.DaggerFragment

abstract class BaseFragment : DaggerFragment(), BaseView {

    override var isRecreated: Boolean = false

    abstract val presenter: ScopedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView(savedInstanceState)
    }

    private fun initializeView(savedInstanceState: Bundle?) {
        isRecreated = savedInstanceState != null
    }

    override fun onPause() {
        super.onPause()
        presenter.deactivate()
    }
}
