package com.example.giphysearch

import com.example.giphysearch.data.repository.GifRepositoryNetworkImpl
import com.example.giphysearch.fake.FakeResponse
import com.example.giphysearch.fake.GifApiServiceFakeImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GifRepositoryNetworkImplTest {

    @Test
    fun gifRepositoryNetworkImpl_getGifs_verifyResponse() = runTest {
        val repository = GifRepositoryNetworkImpl(gifApiService = GifApiServiceFakeImpl())
        assertEquals(FakeResponse.response, repository.getGifs(""))
    }

}
