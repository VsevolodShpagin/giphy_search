package com.example.giphysearch.ui

import com.example.giphysearch.model.Gif

data class GiphySearchUiState(
    val gifs: List<Gif> = listOf(),
    val errorText: String = ""
)
