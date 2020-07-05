package com.marvel.domain.characters.model

import com.marvel.domain.characters.entity.SeriesEntity

data class GetSeriesResult(
    val code: Int,
    val status: String,
    val totalCount: Int,
    val currentCount: Int,
    val paginationOffset: Int,
    val series: List<SeriesEntity>
)
