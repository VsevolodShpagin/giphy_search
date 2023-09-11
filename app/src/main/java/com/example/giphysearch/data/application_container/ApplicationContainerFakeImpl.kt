package com.example.giphysearch.data.application_container

import com.example.giphysearch.data.repository.GifRepository
import com.example.giphysearch.data.repository.GifRepositoryFakeImpl

class ApplicationContainerFakeImpl : ApplicationContainer {

    override val gifRepository: GifRepository
        get() = GifRepositoryFakeImpl()

}
