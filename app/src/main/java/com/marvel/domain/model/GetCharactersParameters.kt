package com.marvel.domain.model


data class GetCharactersParameters(
    val offset: Int,
    val query: String
)