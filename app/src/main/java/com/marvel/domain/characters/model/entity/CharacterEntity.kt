package com.marvel.domain.characters.model.entity

class CharacterEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    val description: String = ""
)
