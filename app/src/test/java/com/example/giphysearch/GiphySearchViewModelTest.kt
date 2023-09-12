package com.example.giphysearch

import com.example.giphysearch.fake.FakeResponse
import com.example.giphysearch.fake.GifRepositoryFakeImpl
import com.example.giphysearch.ui.GiphySearchUiState
import com.example.giphysearch.ui.GiphySearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GiphySearchViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun giphySearchViewModel_updateInputText_verifyTextChange() {
        val viewModel = GiphySearchViewModel(gifRepository = GifRepositoryFakeImpl())
        viewModel.updateInputText("horse")
        assertEquals("horse", viewModel.inputText)
    }

    @Test
    fun giphySearchViewModel_updateInputText_verifyOffsetReset() = runTest {
        val viewModel = GiphySearchViewModel(gifRepository = GifRepositoryFakeImpl())
        assertEquals(0, viewModel.offset)
        viewModel.updateInputText("horse")
        advanceTimeBy(3000L)
        assertEquals(50, viewModel.offset)
        viewModel.updateInputText("red")
        assertEquals(0, viewModel.offset)
    }

    @Test
    fun giphySearchViewModel_getGifs_verifyUiStateSuccess() = runTest {
        val viewModel = GiphySearchViewModel(gifRepository = GifRepositoryFakeImpl())
        viewModel.updateInputText("text")
        advanceTimeBy(3000L)
        assertEquals(
            GiphySearchUiState.Success(FakeResponse.response.gifs),
            viewModel.uiState
        )
    }

    @Test
    fun giphySearchViewModel_getGifs_verifyListAdditionOnScroll() = runTest {
        val viewModel = GiphySearchViewModel(gifRepository = GifRepositoryFakeImpl())
        viewModel.updateInputText("text")
        advanceTimeBy(3000L)
        assertEquals(1, (viewModel.uiState as? GiphySearchUiState.Success)?.gifs?.size)
        viewModel.onListEndReached()
        advanceTimeBy(3000L)
        assertEquals(2, (viewModel.uiState as? GiphySearchUiState.Success)?.gifs?.size)
    }

}
