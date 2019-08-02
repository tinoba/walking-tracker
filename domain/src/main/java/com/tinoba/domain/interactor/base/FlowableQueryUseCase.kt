package com.tinoba.domain.interactor.base

import io.reactivex.Flowable

interface FlowableQueryUseCase<Result> {

    fun run(): Flowable<Result>
}