package com.example.giphysearch.data.application_container

import com.example.giphysearch.data.repository.GifRepository
import com.example.giphysearch.data.repository.GifRepositoryNetworkImpl
import com.example.giphysearch.network.GifApiService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class DefaultApplicationContainerImpl : ApplicationContainer {

    //private val baseUlr = "api.giphy.com/v1/gifs/"
    private val baseUrl = "https://api.giphy.com/v1/gifs/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GifApiService by lazy {
        retrofit.create(GifApiService::class.java)
    }

    override val gifRepository: GifRepository by lazy {
        GifRepositoryNetworkImpl(retrofitService)
    }

}
