package com.tinoba.domain.interactor.base

import io.reactivex.Single

interface QueryUseCase<Result> {

    fun run(): Single<Result>
}