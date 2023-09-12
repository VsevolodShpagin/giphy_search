package com.example.giphysearch.fake

import com.example.giphysearch.model.SearchResponse
import com.example.giphysearch.network.GiphyApiService

class GiphyApiServiceFakeImpl : GiphyApiService {

    override suspend fun getGifs(searchText: String, apiKey: String, offset: Int): SearchResponse {
        return FakeResponse.response
    }

}
