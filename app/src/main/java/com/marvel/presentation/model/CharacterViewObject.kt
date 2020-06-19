package com.marvel.presentation.model

data class CharacterViewObject(
    val id: Int,
    val name: String,
    val bannerURL: String,
    var isFavorite: Boolean
)