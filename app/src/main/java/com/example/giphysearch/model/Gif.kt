package com.example.giphysearch.model

import kotlinx.serialization.Serializable

@Serializable
data class Gif(
    val id: String,
    val url: String
)
