package com.example.giphysearch

import com.example.giphysearch.fake.FakeResponse
import com.example.giphysearch.fake.GiphyApiServiceFakeImpl
import com.example.giphysearch.repository.GifRepositoryNetworkImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GifRepositoryNetworkImplTest {

    @Test
    fun gifRepositoryNetworkImpl_getGifs_verifyResponse() = runTest {
        val repository = GifRepositoryNetworkImpl(giphyApiService = GiphyApiServiceFakeImpl())
        assertEquals(FakeResponse.response, repository.getGifs(""))
    }

}
