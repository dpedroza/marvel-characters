package com.marvel.domain.model.result

import com.marvel.domain.model.entity.ComicsEntity

data class GetComicsResult(
    val code: Int,
    val status: String,
    val totalCount: Int,
    val currentCount: Int,
    val paginationOffset: Int,
    val comics: List<ComicsEntity>
)
