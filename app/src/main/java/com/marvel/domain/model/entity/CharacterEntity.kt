package com.marvel.domain.model.entity

class CharacterEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val description: String? = null,
    val comics: List<ComicsEntity>? = null,
    val series: List<SeriesEntity>? = null
)
