package com.example.giphysearch.pagination

class PaginatorDefaultImpl<Offset, Item>(
    private val initialOffset: Offset,
    private inline val onRequest: suspend (offset: Offset) -> List<Item>,
    private inline val getNextOffset: suspend (items: List<Item>) -> Offset,
    private inline val onSuccess: suspend (items: List<Item>, newOffset: Offset) -> Unit
) : Paginator<Offset, Item> {

    private var currentOffset = initialOffset
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) return
        isMakingRequest = true
        val items = onRequest(currentOffset)
        isMakingRequest = false
        currentOffset = getNextOffset(items)
        onSuccess(items, currentOffset)
    }

    override fun reset() {
        currentOffset = initialOffset
    }

}
