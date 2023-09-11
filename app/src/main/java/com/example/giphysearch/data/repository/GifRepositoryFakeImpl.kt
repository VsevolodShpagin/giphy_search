package com.example.giphysearch.data.repository

import android.util.Log
import com.example.giphysearch.model.Gif
import com.example.giphysearch.model.Image
import com.example.giphysearch.model.Images
import com.example.giphysearch.model.Pagination
import com.example.giphysearch.model.SearchResponse

class GifRepositoryFakeImpl : GifRepository {

    override suspend fun getGifs(searchText: String): SearchResponse {
        if (searchText == "horse") Log.d("getGifs", "HORSE") else Log.d("getGifs", "ELSE")
        return SearchResponse(listOf(Gif("", Images(Image("")))), Pagination(0, 0, 0))
    }

}
