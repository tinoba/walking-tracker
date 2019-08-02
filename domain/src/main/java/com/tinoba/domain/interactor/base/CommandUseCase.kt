package com.tinoba.domain.interactor.base

import io.reactivex.Completable

interface CommandUseCase {

    fun execute(): Completable
}