package com.example.giphysearch.network

import retrofit2.http.GET
import retrofit2.http.Query

interface GifApiService {

    @GET("search")
    suspend fun getGifs(
        @Query("q") searchText: String,
        @Query("api_key") apiKey: String = "123"
        //@Query("api_key") apiKey: String = "PPYq7l2kVJqjqAM3hcNkgerQidztXat5"
    ): String
    //suspend fun getGifs(): List<Gif>

}