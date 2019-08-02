package com.tinoba.template.ui.home

import com.tinoba.template.base.ScopedPresenter

interface HomeContract {

    interface Presenter : ScopedPresenter {

        fun observeLocations()

        fun clearLocations()
    }

    interface View {
        fun render(photoUrls: List<String>)
    }
}