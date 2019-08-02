package com.tinoba.template.base

class NoOpScopedPresenter private constructor() : ScopedPresenter {

    override fun deactivate() {

    }

    companion object {

        var INSTANCE: ScopedPresenter = NoOpScopedPresenter()
    }
}