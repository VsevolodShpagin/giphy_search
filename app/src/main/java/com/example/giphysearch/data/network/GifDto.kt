package com.example.giphysearch.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifDto(
    val id: String,
    val title: String,
    val images: ImagesDto
)

@Serializable
data class ImagesDto(
    @SerialName(value = "fixed_width") val image: ImageDto
)

@Serializable
data class ImageDto(
    val webp: String?
)
