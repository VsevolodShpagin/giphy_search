package com.example.giphysearch.fake

import com.example.giphysearch.model.SearchResponse
import com.example.giphysearch.repository.GifRepository

class GifRepositoryFakeImpl : GifRepository {

    override suspend fun getGifs(searchText: String, offset: Int): SearchResponse {
        return FakeResponse.response
    }

}
