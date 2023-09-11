package com.example.giphysearch.fake

import com.example.giphysearch.data.repository.GifRepository
import com.example.giphysearch.model.SearchResponse

class GifRepositoryFakeImpl : GifRepository {

    override suspend fun getGifs(searchText: String): SearchResponse {
        return FakeResponse.response
    }

}
