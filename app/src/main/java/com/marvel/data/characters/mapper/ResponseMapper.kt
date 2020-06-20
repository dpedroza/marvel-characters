package com.marvel.data.characters.mapper

import com.marvel.data.model.MarvelServiceApiResponse
import com.marvel.domain.model.GetCharactersResultEntity

interface ResponseMapper {

    fun toEntityList(
        localFavorites: List<Int>,
        remoteCharacters: MarvelServiceApiResponse
    ): GetCharactersResultEntity
}