package com.example.giphysearch.ui

import com.example.giphysearch.model.Gif

sealed interface GiphySearchUiState {

    data class Success(val gifs: List<Gif>) : GiphySearchUiState

    data class Error(val errorText: String) : GiphySearchUiState

}
