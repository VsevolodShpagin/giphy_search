package com.example.giphysearch

import com.example.giphysearch.fake.FakeResponse
import com.example.giphysearch.fake.GifRepositoryFakeImpl
import com.example.giphysearch.ui.GiphySearchUiState
import com.example.giphysearch.ui.GiphySearchViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GiphySearchViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun giphySearchViewModel_updateInputText_verifyTextChange() {
        val giphySearchViewModel = GiphySearchViewModel(gifRepository = GifRepositoryFakeImpl())
        giphySearchViewModel.updateInputText("horse")
        assertEquals("horse", giphySearchViewModel.inputText)
    }

    @Test
    fun giphySearchViewModel_getGifs_verifyUiStateSuccess() = runTest {
        val giphySearchViewModel = GiphySearchViewModel(gifRepository = GifRepositoryFakeImpl())
        giphySearchViewModel.updateInputText("")
        assertEquals(
            GiphySearchUiState.Success(FakeResponse.response),
            giphySearchViewModel.giphySearchUiState
        )
    }

}
