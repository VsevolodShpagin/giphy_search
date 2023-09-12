package com.example.giphysearch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.giphysearch.ui.screens.ResultScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiphySearchApp() {
    val viewModel: GiphySearchViewModel = viewModel(factory = GiphySearchViewModel.Factory)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = viewModel.inputText,
            onValueChange = viewModel::updateInputText,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        ResultScreen(
            uiState = viewModel.uiState,
            onListEndReached = { viewModel.onListEndReached() }
        )
    }
}
