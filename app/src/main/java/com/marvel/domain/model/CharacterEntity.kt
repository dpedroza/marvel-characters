package com.marvel.domain.model

class CharacterEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val description: String? = null,
    val comics: List<ComicEntity>? = null,
    val series: List<SeriesEntity>? = null
)