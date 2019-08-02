package com.tinoba.template.base

import com.tinoba.template.dagger.application.module.ThreadingModule
import com.tinoba.template.router.Router
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Named

abstract class BasePresenter<View>(view: View) : ScopedPresenter {

    @Inject
    @field:Named(ThreadingModule.MAIN_SCHEDULER)
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    @field:Named(ThreadingModule.BACKGROUND_SCHEDULER)
    lateinit var backgroundScheduler: Scheduler

    @Inject
    lateinit var router: Router

    private val disposables = CompositeDisposable()
    private val viewReference: WeakReference<View> = WeakReference(view)

    protected fun getView() = viewReference.get()

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun deactivate() {
        disposables.clear()
    }
}
