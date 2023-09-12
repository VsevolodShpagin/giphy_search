package com.example.giphysearch

import com.example.giphysearch.network.GiphyApiService
import com.example.giphysearch.repository.GifRepositoryNetworkImpl
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GifRepositoryNetworkImplTest {

    private val mockApiService = mockk<GiphyApiService>(relaxed = true)

    private val repository = GifRepositoryNetworkImpl(giphyApiService = mockApiService)

    @Test
    fun gifRepositoryNetworkImpl_getGifs_verifyCallWithDefaultOffset() = runTest {
        repository.getGifs("text")
        coVerify { mockApiService.getGifs(searchText = "text", offset = 0) }
    }

    @Test
    fun gifRepositoryNetworkImpl_getGifs_verifyCallWithPassedOffset() = runTest {
        repository.getGifs("text", 50)
        coVerify { mockApiService.getGifs(searchText = "text", offset = 50) }
    }

}
