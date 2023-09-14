package com.example.giphysearch.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.giphysearch.ui.GiphySearchUiState

@Composable
fun ResultScreen(
    uiState: GiphySearchUiState,
    onListEndReached: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is GiphySearchUiState.Success -> SuccessScreen(
            gifs = uiState.gifs,
            onListEndReached = onListEndReached,
            modifier = modifier
        )

        is GiphySearchUiState.Blank -> BlankScreen(
            infoText = uiState.infoText,
            modifier = modifier
        )

        is GiphySearchUiState.Error -> ErrorScreen(
            errorText = uiState.errorText,
            modifier = modifier
        )
    }
}
