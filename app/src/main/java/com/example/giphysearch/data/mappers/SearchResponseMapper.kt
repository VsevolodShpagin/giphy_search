package com.example.giphysearch.data.mappers

import com.example.giphysearch.data.network.SearchResponseDto
import com.example.giphysearch.domain.SearchResponse

fun SearchResponseDto.toSearchResponse(): SearchResponse {
    return SearchResponse(
        gifs = gifs.map { gifDto -> gifDto.toGif() },
        pagination = pagination.toPagination()
    )
}
