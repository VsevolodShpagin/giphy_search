package com.example.giphysearch.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.giphysearch.R

@Composable
fun BlankScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(
            text = stringResource(R.string.blank_screen_text),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
        )
    }
}
