package com.example.giphysearch.fake

import com.example.giphysearch.model.Gif
import com.example.giphysearch.model.Image
import com.example.giphysearch.model.Images
import com.example.giphysearch.model.Pagination
import com.example.giphysearch.model.SearchResponse

object FakeResponse {

    val response: SearchResponse =
        SearchResponse(listOf(Gif("", "", Images(Image("")))), Pagination(0, 50, 0))

}
