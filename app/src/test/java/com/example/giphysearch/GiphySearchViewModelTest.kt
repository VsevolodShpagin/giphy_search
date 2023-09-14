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
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class GiphySearchViewModelTest {

    private val mockRepository = mockk<GifRepository>(relaxed = true)
    private val mockSearchResponse = mockk<SearchResponse>(relaxed = true)
    private val mockGif = mockk<Gif>(relaxed = true)
    private val mockIOException = mockk<IOException>(relaxed = true)
    private val mockHttpException = mockk<HttpException>(relaxed = true)

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
        coEvery { mockRepository.getGifs("horse", offset = 0) } returns mockSearchResponse
        coEvery { mockSearchResponse.pagination.count } returns 50
        viewModel.updateInputText("horse")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", offset = 0) }
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", offset = 50) }
    }

    @Test
    fun giphySearchViewModel_updateInputText_verifyOffsetReset() = runTest {
        coEvery { mockRepository.getGifs("horse", offset = 0) } returns mockSearchResponse
        coEvery { mockSearchResponse.pagination.count } returns 50
        viewModel.updateInputText("horse")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", offset = 0) }
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("horse", offset = 50) }
        viewModel.updateInputText("red")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("red", offset = 0) }
    }

    @Test
    fun giphySearchViewModel_getGifs_verifyUiStateSuccess() = runTest {
        coEvery { mockRepository.getGifs("text", offset = 0) } returns mockSearchResponse
        coEvery { mockSearchResponse.gifs } returns listOf(mockGif, mockGif)
        viewModel.updateInputText("text")
        advanceTimeBy(1500L)
        assertEquals(GiphySearchUiState.Success(listOf(mockGif, mockGif)), viewModel.uiState)
    }

    @Test
    fun giphySearchViewModel_getGifs_verifyListAdditionOnScroll() = runTest {
        coEvery { mockRepository.getGifs("text", offset = any()) } returns mockSearchResponse
        coEvery { mockSearchResponse.pagination.count } returnsMany listOf(2, 1)
        coEvery { mockSearchResponse.gifs } returnsMany listOf(
            listOf(mockGif, mockGif), listOf(mockGif)
        )
        viewModel.updateInputText("text")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("text", offset = 0) }
        assertEquals(2, (viewModel.uiState as? GiphySearchUiState.Success)?.gifs?.size)
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs("text", offset = 2) }
        assertEquals(3, (viewModel.uiState as? GiphySearchUiState.Success)?.gifs?.size)
    }

    @Test
    fun giphySearchViewModel_onIOException_verifyErrorText() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "text", offset = 0)
        } throws mockIOException
        coEvery { mockIOException.message } returns "exception message"
        viewModel.updateInputText("text")
        advanceTimeBy(1500L)
        assertEquals(GiphySearchUiState.Error("exception message"), viewModel.uiState)
    }

    @Test
    fun giphySearchViewModel_onHttpException_verifyErrorText() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "text", offset = 0)
        } throws mockHttpException
        coEvery { mockHttpException.response()?.errorBody().toString() } returns "error"
        viewModel.updateInputText("text")
        advanceTimeBy(1500L)
        assertEquals(GiphySearchUiState.Error("error"), viewModel.uiState)
    }

}
