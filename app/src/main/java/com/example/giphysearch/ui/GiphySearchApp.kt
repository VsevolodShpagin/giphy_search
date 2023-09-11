@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.giphysearch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.giphysearch.ui.screens.ResultScreen
import com.example.giphysearch.ui.theme.GiphySearchTheme

@Composable
fun GiphySearchApp() {
    val giphySearchViewModel: GiphySearchViewModel =
        viewModel(factory = GiphySearchViewModel.Factory)
    Column {
        TextField(
            value = giphySearchViewModel.inputText,
            onValueChange = giphySearchViewModel::updateInputText
        )
        ResultScreen(uiState = giphySearchViewModel.giphySearchUiState)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GiphySearchAppPreview() {
    GiphySearchTheme {
        GiphySearchApp()
    }
}
