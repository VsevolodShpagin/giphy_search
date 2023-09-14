package com.example.giphysearch.repository

import com.example.giphysearch.domain.SearchResponse

interface GifRepository {
    suspend fun getGifs(searchText: String, limit: Int = 50, offset: Int = 0): SearchResponse

}
