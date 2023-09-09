package com.example.giphysearch.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName(value = "total_count") val totalCount: Int,
    val count: Int,
    val offset: Int
)
