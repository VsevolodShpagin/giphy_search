package com.example.giphysearch.ui

import androidx.annotation.StringRes
import com.example.giphysearch.domain.Gif

sealed interface GiphySearchUiState {

    data class Blank(@StringRes val infoText: Int) : GiphySearchUiState

    data class Success(val gifs: List<Gif>) : GiphySearchUiState

    data class Error(val errorText: String) : GiphySearchUiState

}
