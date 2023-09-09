package com.example.giphysearch.data.repository

interface GifRepository {
    suspend fun getGifs(searchText: String): String
    //suspend fun getGifs(): List<Gif>

}
