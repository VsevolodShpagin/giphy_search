package com.example.giphysearch.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationDto(
    @SerialName(value = "total_count") val totalCount: Int,
    val count: Int,
    val offset: Int
)
