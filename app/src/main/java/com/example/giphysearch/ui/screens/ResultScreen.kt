package com.example.giphysearch.ui.screens

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    gifs: String,
    //gifs: List<Gif>,
    modifier: Modifier = Modifier
) {
    Text(text = gifs, modifier = modifier)
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(150.dp),
//        modifier = modifier
//    ) {
//        items(items = gifs) { item ->
//            GifCard(gif = item)
//        }
//    }
}

@Composable
fun GifCard(gif: Gif, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(text = gif.ph)
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
