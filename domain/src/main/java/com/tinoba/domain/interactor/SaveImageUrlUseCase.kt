package com.tinoba.domain.interactor

import com.tinoba.domain.interactor.base.CommandUseCaseWithParam
import com.tinoba.domain.model.PhotoLocation
import com.tinoba.domain.repository.ImagesRepository

class SaveImageUrlUseCase(private val imagesRepository: ImagesRepository) :
    CommandUseCaseWithParam<PhotoLocation> {

    override fun execute(photoLocation: PhotoLocation) = imagesRepository.fetchAndStorePhoto(photoLocation)
}