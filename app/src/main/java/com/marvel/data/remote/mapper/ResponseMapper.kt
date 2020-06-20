package com.marvel.data.remote.mapper

import com.marvel.data.model.MarvelServiceApiResponse
import com.marvel.domain.model.GetCharactersResultEntity

interface ResponseMapper {

    fun toEntityList(
        localFavorites: List<Int>,
        remoteCharacters: MarvelServiceApiResponse
    ): GetCharactersResultEntity
}