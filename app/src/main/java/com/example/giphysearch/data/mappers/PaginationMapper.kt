package com.example.giphysearch.data.mappers

import com.example.giphysearch.data.network.PaginationDto
import com.example.giphysearch.domain.Pagination

fun PaginationDto.toPagination(): Pagination {
    return Pagination(
        totalCount = totalCount,
        count = count,
        offset = offset
    )
}
