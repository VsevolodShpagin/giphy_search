package com.example.giphysearch.data.application_container

import com.example.giphysearch.data.repository.GifRepository

interface ApplicationContainer {

    val gifRepository: GifRepository

}
