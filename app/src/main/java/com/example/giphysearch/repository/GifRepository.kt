package com.example.giphysearch.repository

import com.example.giphysearch.model.SearchResponse

interface GifRepository {
    suspend fun getGifs(searchText: String, offset: Int = 0): SearchResponse

}
