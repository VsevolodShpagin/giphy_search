package com.example.giphysearch.ui

sealed interface GiphySearchUiState {

    data class Success(val gifs: String) : GiphySearchUiState

    //data class Success(val gifs: List<Gif>) : GiphySearchUiState
    data class Error(val errorText: String) : GiphySearchUiState
//    object Error : GiphySearchUiState

}
