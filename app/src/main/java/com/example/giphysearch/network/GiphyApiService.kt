package com.example.giphysearch.network

import com.example.giphysearch.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApiService {

    @GET("search")
    suspend fun getGifs(
        @Query("q") searchText: String,
        @Query("api_key") apiKey: String = "PPYq7l2kVJqjqAM3hcNkgerQidztXat5",
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): SearchResponse

}
