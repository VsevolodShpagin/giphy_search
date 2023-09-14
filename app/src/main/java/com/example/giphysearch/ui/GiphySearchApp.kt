package com.example.giphysearch.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.giphysearch.R
import com.example.giphysearch.ui.screens.ResultScreen
import com.example.giphysearch.ui.theme.GiphySearchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiphySearchApp() {
    Scaffold(
        topBar = { GiphySearchTopAppBar() })
    {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
            ) {
                val viewModel: GiphySearchViewModel =
                    viewModel(factory = GiphySearchViewModel.Factory)
                TextField(
                    value = viewModel.inputText,
                    onValueChange = viewModel::onInputTextChanged,
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
                    onListEndReached = viewModel::onListEndReached
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiphySearchTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.pepper),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.image_size))
                        .padding(dimensionResource(id = R.dimen.padding_tiny))
                )
                Text(
                    text = stringResource(R.string.top_bar_title),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    GiphySearchTheme {
        GiphySearchTopAppBar()
    }
}
