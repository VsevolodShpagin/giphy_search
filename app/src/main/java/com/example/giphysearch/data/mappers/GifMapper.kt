package com.example.giphysearch.data.mappers

import com.example.giphysearch.data.network.GifDto
import com.example.giphysearch.domain.Gif

fun GifDto.toGif(): Gif {
    return Gif(
        id = id,
        title = title,
        webp = images.image.webp
    )
}
