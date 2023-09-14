package com.example.giphysearch

import com.example.giphysearch.model.Gif
import com.example.giphysearch.model.SearchResponse
import com.example.giphysearch.repository.GifRepository
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
    fun giphySearchViewModel_onInputTextChanged_verifyTextChange() {
        viewModel.onInputTextChanged("horse")
        assertEquals("horse", viewModel.inputText)
    }

    @Test
    fun giphySearchViewModel_onInputTextChanged_verifyOffsetIncrease() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "horse", offset = 0)
        } returns mockSearchResponse
        coEvery { mockSearchResponse.gifs } returns listOf(mockGif, mockGif, mockGif)
        viewModel.onInputTextChanged("horse")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs(searchText = "horse", offset = 0) }
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs(searchText = "horse", offset = 3) }
    }

    @Test
    fun giphySearchViewModel_onInputTextChanged_verifyOffsetReset() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "horse", offset = 0)
        } returns mockSearchResponse
        coEvery { mockSearchResponse.gifs } returns listOf(mockGif, mockGif, mockGif)
        viewModel.onInputTextChanged("horse")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs(searchText = "horse", offset = 0) }
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs(searchText = "horse", offset = 3) }
        viewModel.onInputTextChanged("red")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs(searchText = "red", offset = 0) }
    }

    @Test
    fun giphySearchViewModel_onInputTextChanged_verifyGifsReturned() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "text", offset = 0)
        } returns mockSearchResponse
        coEvery { mockSearchResponse.gifs } returns listOf(mockGif, mockGif)
        viewModel.onInputTextChanged("text")
        advanceTimeBy(1500L)
        assertEquals(2, viewModel.uiState.gifs.size)
    }

    @Test
    fun giphySearchViewModel_onListEndReached_verifyListAdditionOnScroll() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "text", offset = any())
        } returns mockSearchResponse
        coEvery { mockSearchResponse.gifs } returnsMany listOf(
            listOf(mockGif, mockGif), listOf(mockGif)
        )
        viewModel.onInputTextChanged("text")
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs(searchText = "text", offset = 0) }
        assertEquals(2, viewModel.uiState.gifs.size)
        viewModel.onListEndReached()
        advanceTimeBy(1500L)
        coVerify { mockRepository.getGifs(searchText = "text", offset = 2) }
        assertEquals(3, viewModel.uiState.gifs.size)
    }

    @Test
    fun giphySearchViewModel_onIOException_verifyErrorText() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "text", offset = 0)
        } throws mockIOException
        coEvery { mockIOException.message } returns "exception message"
        viewModel.onInputTextChanged("text")
        advanceTimeBy(1500L)
        assertEquals("exception message", viewModel.uiState.errorText)
    }

    @Test
    fun giphySearchViewModel_onHttpException_verifyErrorText() = runTest {
        coEvery {
            mockRepository.getGifs(searchText = "text", offset = 0)
        } throws mockHttpException
        coEvery { mockHttpException.response()?.errorBody().toString() } returns "error"
        viewModel.onInputTextChanged("text")
        advanceTimeBy(1500L)
        assertEquals("error", viewModel.uiState.errorText)
    }

}
