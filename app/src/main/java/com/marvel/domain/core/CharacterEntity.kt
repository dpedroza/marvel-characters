package com.marvel.domain.core

import com.marvel.domain.characters.entity.ComicsEntity
import com.marvel.domain.characters.entity.SeriesEntity

class CharacterEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val description: String? = null,
    val comics: List<ComicsEntity>? = null,
    val series: List<SeriesEntity>? = null
)
