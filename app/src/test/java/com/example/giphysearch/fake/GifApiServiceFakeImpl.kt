package com.example.giphysearch.fake

import com.example.giphysearch.network.GifApiService

class GifApiServiceFakeImpl : GifApiService {

    override suspend fun getGifs(searchText: String, apiKey: String): String {
        return FakeResponse.response
    }

}
