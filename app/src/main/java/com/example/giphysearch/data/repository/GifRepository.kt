package com.example.giphysearch.data.repository

import com.example.giphysearch.model.SearchResponse

interface GifRepository {
    suspend fun getGifs(searchText: String): SearchResponse

}
