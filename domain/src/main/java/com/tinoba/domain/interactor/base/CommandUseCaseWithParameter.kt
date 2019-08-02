package com.tinoba.domain.interactor.base

import io.reactivex.Completable

interface CommandUseCaseWithParam<Param> {

    fun execute(param: Param): Completable
}