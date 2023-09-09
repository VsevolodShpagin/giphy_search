package com.example.giphysearch.fake

import com.example.giphysearch.data.repository.GifRepository

class GifRepositoryFakeImpl : GifRepository {

    override suspend fun getGifs(searchText: String): String {
        return FakeResponse.response
    }

}
