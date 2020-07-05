package com.marvel.domain.characters.model

import com.marvel.domain.core.CharacterEntity

data class GetCharactersResult(
    val code: Int,
    val status: String,
    val totalCount: Int,
    val currentCount: Int,
    val paginationOffset: Int,
    val characters: List<CharacterEntity>
)
