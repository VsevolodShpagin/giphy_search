package com.example.giphysearch.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    @SerialName(value = "data") val gifs: List<GifDto>,
    val pagination: PaginationDto
)
