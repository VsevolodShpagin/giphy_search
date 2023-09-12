package com.example.giphysearch.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.giphysearch.R
import com.example.giphysearch.model.Gif
import com.example.giphysearch.model.Image
import com.example.giphysearch.model.Images
import com.example.giphysearch.ui.theme.GiphySearchTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuccessScreen(
    gifs: List<Gif>,
    onListEndReached: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_tiny)),
        modifier = modifier
    ) {
        itemsIndexed(items = gifs) { i, item ->
            if (i == gifs.size - 10) onListEndReached.invoke()
            GifCard(
                gif = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_tiny))
            )
        }
    }
}

@Composable
fun GifCard(gif: Gif, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(gif.images.image.webp)
            .crossfade(true)
            .build()
        AsyncImage(
            model = imageRequest,
            contentDescription = gif.title,
            //placeholder = painterResource(id = R.drawable.loading),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessScreenPreview() {
    GiphySearchTheme {
        SuccessScreen(
            gifs = listOf(
                Gif("horse1", "horse1", Images(Image(""))),
                Gif("horse2", "horse2", Images(Image(""))),
                Gif("horse3", "horse3", Images(Image(""))),
                Gif("horse4", "horse4", Images(Image("")))
            ),
            onListEndReached = {}
        )
    }
}
