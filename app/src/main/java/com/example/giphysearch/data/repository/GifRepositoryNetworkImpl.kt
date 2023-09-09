package com.example.giphysearch.data.repository

import com.example.giphysearch.network.GifApiService

class GifRepositoryNetworkImpl(
    private val gifApiService: GifApiService
) : GifRepository {

    override suspend fun getGifs(searchText: String): String {
        return gifApiService.getGifs(searchText = searchText)
    }

//    override suspend fun getGifs(): List<Gif> {
//        return gifApiService.getGifs()
//    }

}
