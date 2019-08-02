package com.tinoba.domain.interactor

import com.tinoba.domain.interactor.base.CommandUseCase
import com.tinoba.domain.repository.ImagesRepository

/**
 * Clears the database on each new app start, in real scenario we would keep it for history
 */
class ClearImageUrlsUseCase(private val imagesRepository: ImagesRepository) :
    CommandUseCase {

    override fun execute() = imagesRepository.clearUrls()
}