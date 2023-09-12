package com.example.giphysearch.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
    ) {
        itemsIndexed(items = gifs) { i, item ->
            if (i == gifs.size - 10) onListEndReached.invoke()
            GifCard(
                gif = item,
                index = i,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun GifCard(gif: Gif, index: Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(gif.images.image.webp)
            .build()
        Box {
            AsyncImage(
                model = imageRequest,
                contentDescription = gif.title,
                placeholder = painterResource(id = R.drawable.horse),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = index.toString(),
                color = Color.White,
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
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