package com.tinoba.template.base

import android.os.Bundle
import com.tinoba.template.dagger.activity.DaggerActivity

abstract class BaseActivity : DaggerActivity() {

    private var isViewRecreated: Boolean = false

    protected abstract fun getPresenter(): ScopedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView(savedInstanceState)
    }

    private fun initializeView(savedInstanceState: Bundle?) {
        isViewRecreated = savedInstanceState != null
    }

    override fun onPause() {
        getPresenter().deactivate()
        super.onPause()
    }
}
