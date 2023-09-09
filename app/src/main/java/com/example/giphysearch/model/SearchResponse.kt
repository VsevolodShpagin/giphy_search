package com.example.giphysearch.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName(value = "data") val gifs: List<Gif>,
    val pagination: Pagination
)
