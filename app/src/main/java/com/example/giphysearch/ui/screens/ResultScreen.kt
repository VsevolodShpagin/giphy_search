package com.example.giphysearch.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.giphysearch.ui.GiphySearchUiState

@Composable
fun ResultScreen(
    uiState: GiphySearchUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is GiphySearchUiState.Success -> SuccessScreen(
            gifs = uiState.gifs,
            modifier = modifier
        )

        is GiphySearchUiState.Blank -> BlankScreen(
            modifier = modifier
        )

        is GiphySearchUiState.Error -> ErrorScreen(
            errorText = uiState.errorText,
            modifier = modifier
        )
    }
}
