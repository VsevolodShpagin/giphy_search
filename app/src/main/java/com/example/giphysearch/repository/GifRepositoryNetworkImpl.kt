package com.example.giphysearch.repository

import com.example.giphysearch.model.SearchResponse
import com.example.giphysearch.network.GiphyApiService

class GifRepositoryNetworkImpl(
    private val giphyApiService: GiphyApiService
) : GifRepository {

    override suspend fun getGifs(searchText: String, limit: Int, offset: Int): SearchResponse {
        return giphyApiService.getGifs(searchText = searchText, limit = limit, offset = offset)
    }

}
