package com.example.giphysearch

import com.example.giphysearch.model.Gif
import com.example.giphysearch.model.SearchResponse
import com.example.giphysearch.repository.GifRepository
import com.example.giphysearch.ui.GiphySearchUiState
import com.example.giphysearch.ui.GiphySearchViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GiphySearchViewModelTest {

    private val mockRepository = mockk<GifRepository>(relaxed = true)
    private val mockSearchResponse = mockk<SearchResponse>(relaxed = true)
    private val mockGif = mockk<Gif>(relaxed = true)

    private val viewModel = GiphySearchViewModel(mockRepository)

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun giphySearchViewModel_updateInputText_verifyTextChange() {
        viewModel.updateInputText("horse")
        assertEquals("horse", viewModel.inputText)
    }

    @Test
    fun giphySearchViewModel_updateInputText_verifyOffsetIncrease() = runTest {
        coEvery { mockRepository.getGifs("horse", 0) } returns mockSearchResponse
        coEvery { mockSearchResponse.pagination.count } returns 50
        viewModel.updateInputText("horse")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", 0) }
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", 50) }
    }

    @Test
    fun giphySearchViewModel_updateInputText_verifyOffsetReset() = runTest {
        coEvery { mockRepository.getGifs("horse", 0) } returns mockSearchResponse
        coEvery { mockSearchResponse.pagination.count } returns 50
        viewModel.updateInputText("horse")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", 0) }
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", 50) }
        viewModel.updateInputText("red")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("red", 0) }
    }

    @Test
    fun giphySearchViewModel_getGifs_verifyUiStateSuccess() = runTest {
        coEvery { mockRepository.getGifs("text", 0) } returns mockSearchResponse
        coEvery { mockSearchResponse.gifs } returns listOf(mockGif, mockGif)
        viewModel.updateInputText("text")
        advanceTimeBy(1500L)
        assertEquals(GiphySearchUiState.Success(listOf(mockGif, mockGif)), viewModel.uiState)
    }

    @Test
    fun giphySearchViewModel_getGifs_verifyListAdditionOnScroll() = runTest {
        coEvery { mockRepository.getGifs("text", any()) } returns mockSearchResponse
        coEvery { mockSearchResponse.pagination.count } returnsMany listOf(2, 1)
        coEvery { mockSearchResponse.gifs } returnsMany listOf(
            listOf(mockGif, mockGif), listOf(mockGif)
        )
        viewModel.updateInputText("text")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("text", 0) }
        assertEquals(2, (viewModel.uiState as? GiphySearchUiState.Success)?.gifs?.size)
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("text", 2) }
        assertEquals(3, (viewModel.uiState as? GiphySearchUiState.Success)?.gifs?.size)
    }

}
