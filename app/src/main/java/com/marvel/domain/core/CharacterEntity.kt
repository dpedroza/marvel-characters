package com.marvel.domain.core

class CharacterEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val description: String = ""
)
