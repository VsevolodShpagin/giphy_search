package com.example.giphysearch.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.giphysearch.R
import com.example.giphysearch.ui.theme.GiphySearchTheme

@Composable
fun ErrorScreen(errorText: String, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(
            text = errorText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
    GiphySearchTheme {
        ErrorScreen(errorText = "Sadness")
    }
}
