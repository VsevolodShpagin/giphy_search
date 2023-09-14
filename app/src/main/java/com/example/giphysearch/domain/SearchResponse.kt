package com.example.giphysearch.domain

data class SearchResponse(
    val gifs: List<Gif>,
    val pagination: Pagination
)
