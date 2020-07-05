package com.marvel.domain.characters.model

import com.marvel.domain.characters.entity.ComicsEntity

data class GetComicsResult(
    val code: Int,
    val status: String,
    val totalCount: Int,
    val currentCount: Int,
    val paginationOffset: Int,
    val comics: List<ComicsEntity>
)
