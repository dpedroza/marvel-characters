package com.marvel.domain.model

data class GetCharactersResultEntity(
    val code: Int,
    val status: String,
    val totalCount: Int,
    val currentCount: Int,
    val paginationOffset: Int,
    val characters: List<CharacterEntity>
)