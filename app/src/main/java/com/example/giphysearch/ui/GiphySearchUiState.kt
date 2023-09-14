package com.example.giphysearch.ui

import com.example.giphysearch.model.Gif

data class GiphySearchUiState(
    val gifs: List<Gif> = emptyList(),
    val endReached: Boolean = false,
    val offset: Int = 0,
    val errorText: String = ""
)
