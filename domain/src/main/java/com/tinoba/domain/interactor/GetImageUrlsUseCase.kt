package com.tinoba.domain.interactor

import com.tinoba.domain.interactor.base.FlowableQueryUseCase
import com.tinoba.domain.model.Image
import com.tinoba.domain.repository.ImagesRepository

class GetImageUrlsUseCase(private val imagesRepository: ImagesRepository) :
    FlowableQueryUseCase<List<Image>> {

    override fun run() = imagesRepository.fotoUrls()
}