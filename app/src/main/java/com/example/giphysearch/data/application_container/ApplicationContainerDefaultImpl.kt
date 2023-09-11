package com.example.giphysearch.data.application_container

import com.example.giphysearch.data.repository.GifRepository
import com.example.giphysearch.data.repository.GifRepositoryNetworkImpl
import com.example.giphysearch.network.GifApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class ApplicationContainerDefaultImpl : ApplicationContainer {

    //private val baseUlr = "api.giphy.com/v1/gifs/"
    private val baseUrl = "https://api.giphy.com/v1/gifs/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GifApiService by lazy {
        retrofit.create(GifApiService::class.java)
    }

    override val gifRepository: GifRepository by lazy {
        GifRepositoryNetworkImpl(retrofitService)
    }

}
