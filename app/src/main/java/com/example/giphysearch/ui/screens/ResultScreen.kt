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
    if (uiState.gifs.isNotEmpty()) {
        SuccessScreen(
            uiState = uiState,
            onListEndReached = onListEndReached,
            modifier = modifier
        )
    } else if (uiState.errorText.isNotBlank()) {
        ErrorScreen(
            errorText = uiState.errorText,
            modifier = modifier
        )
    } else
        BlankScreen(
            modifier = modifier
        )
}
