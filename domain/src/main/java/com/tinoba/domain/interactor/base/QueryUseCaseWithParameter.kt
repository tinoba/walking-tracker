package com.tinoba.domain.interactor.base

import io.reactivex.Single

interface QueryUseCaseWithParameter<Param, Result> {

    fun run(param: Param): Single<Result>
}
