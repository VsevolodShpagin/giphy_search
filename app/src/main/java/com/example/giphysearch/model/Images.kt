package com.example.giphysearch.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName(value = "fixed_width") val image: Image
)
