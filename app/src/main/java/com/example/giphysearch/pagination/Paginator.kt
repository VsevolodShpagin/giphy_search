package com.example.giphysearch.pagination

//from https://www.youtube.com/@PhilippLackner
interface Paginator<Offset, Item> {
    suspend fun loadNextItems()
    fun reset()
}
