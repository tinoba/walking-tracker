package com.tinoba.template.base

interface ScopedPresenter {

    fun deactivate()

    companion object {

        val EMPTY = NoOpScopedPresenter.INSTANCE
    }
}