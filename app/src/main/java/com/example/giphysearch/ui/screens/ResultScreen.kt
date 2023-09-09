package com.example.giphysearch.ui.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
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
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        items(items = gifs, key = { gif -> gif.id }) { item ->
            GifCard(
                gif = item,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun GifCard(gif: Gif, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(gif.images.image.webp)
            .crossfade(true)
            .build()
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                add(if (SDK_INT >= 28) ImageDecoderDecoder.Factory() else GifDecoder.Factory())
            }
            .build()
        AsyncImage(
            model = imageRequest,
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ErrorScreen(errorText: String, modifier: Modifier = Modifier) {
    Text(text = errorText, modifier = modifier)
}
