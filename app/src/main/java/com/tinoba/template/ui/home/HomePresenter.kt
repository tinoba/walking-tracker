package com.tinoba.template.ui.home

import com.tinoba.domain.interactor.ClearImageUrlsUseCase
import com.tinoba.domain.interactor.GetImageUrlsUseCase
import com.tinoba.template.base.BasePresenter
import javax.inject.Inject

class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View>(view), HomeContract.Presenter {

    @Inject
    lateinit var getImageUrlsUseCase: GetImageUrlsUseCase
    @Inject
    lateinit var clearImageUrlsUseCase: ClearImageUrlsUseCase

    /**
     * On app start, clears the database and observers it for the future updates which
     * will automatically update the UI
     */
    override fun observeLocations() {
        addDisposable(
            getImageUrlsUseCase.run()
                .subscribeOn(backgroundScheduler)
                .map { it.map { it.url } }
                .observeOn(mainThreadScheduler)
                .subscribe(::onGetPhotoUrlSuccess, ::onGetPhotoUrlFailure)
        )
    }

    private fun onGetPhotoUrlSuccess(imageUrls: List<String>) {
        getView()?.render(imageUrls)
    }

    private fun onGetPhotoUrlFailure(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun clearLocations() {
        addDisposable(
            clearImageUrlsUseCase.execute()
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe({}, Throwable::printStackTrace)
        )
    }
}