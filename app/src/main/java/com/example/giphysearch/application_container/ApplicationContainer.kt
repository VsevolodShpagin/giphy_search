package com.example.giphysearch.application_container

import com.example.giphysearch.repository.GifRepository

interface ApplicationContainer {

    val gifRepository: GifRepository

}
