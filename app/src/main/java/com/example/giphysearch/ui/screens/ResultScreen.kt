package com.example.giphysearch.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.giphysearch.model.Gif

@Composable
fun ResultScreen(
    gifs: List<Gif>,
    errorText: String,
    //uiState: GiphySearchUiState,
    onListEndReached: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (gifs.isNotEmpty()) {
        SuccessScreen(
            gifs = gifs,
            onListEndReached = onListEndReached,
            modifier = modifier
        )
    } else if (errorText.isNotBlank()) {
        ErrorScreen(
            errorText = errorText,
            modifier = modifier
        )
    } else {
        BlankScreen(
            modifier = modifier
        )
    }
}
