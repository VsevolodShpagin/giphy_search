package com.example.giphysearch.application_container

import com.example.giphysearch.network.GiphyApiService
import com.example.giphysearch.repository.GifRepository
import com.example.giphysearch.repository.GifRepositoryNetworkImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
class ApplicationContainerNetworkImpl : ApplicationContainer {

    private val json = Json {
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(GiphyApiService.BASE_URL)
        .build()

    private val retrofitService: GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }

    override val gifRepository: GifRepository by lazy {
        GifRepositoryNetworkImpl(retrofitService)
    }

}
