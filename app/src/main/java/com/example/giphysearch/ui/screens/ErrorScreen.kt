package com.example.giphysearch.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.giphysearch.ui.theme.GiphySearchTheme

@Composable
fun ErrorScreen(errorText: String, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(
            text = errorText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(4.dp)
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
