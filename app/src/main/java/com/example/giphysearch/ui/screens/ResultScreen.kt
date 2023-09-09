package com.example.giphysearch.ui.screens

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.giphysearch.model.Gif
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

        is GiphySearchUiState.Error -> ErrorScreen(
            errorText = uiState.errorText,
            modifier = modifier
        )
    }
}

@Composable
fun SuccessScreen(
    gifs: List<Gif>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
    ) {
        items(items = gifs, key = { gif -> gif.id }) { item ->
            GifCard(gif = item)
        }
    }
}

@Composable
fun GifCard(gif: Gif, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(text = gif.url)
//        AsyncImage(
//            model = ImageRequest.Builder(context = LocalContext.current).data(gif).build(),
//            contentDescription = null
//        )
    }
}

@Composable
fun ErrorScreen(errorText: String, modifier: Modifier = Modifier) {
    Text(text = errorText, modifier = modifier)
}
