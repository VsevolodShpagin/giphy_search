package com.example.giphysearch.data.repository

import com.example.giphysearch.model.SearchResponse
import com.example.giphysearch.network.GifApiService

class GifRepositoryNetworkImpl(
    private val gifApiService: GifApiService
) : GifRepository {

    override suspend fun getGifs(searchText: String): SearchResponse {
        return gifApiService.getGifs(searchText = searchText)
    }

}
