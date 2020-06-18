package com.marvel.data.mapper

import com.marvel.data.model.Character
import com.marvel.data.model.MarvelServiceApiResponse
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.presentation.mapper.Mapper

class ResponseMapper : Mapper<MarvelServiceApiResponse, GetCharactersResultEntity> {

    override fun transform(input: MarvelServiceApiResponse): GetCharactersResultEntity {

        return GetCharactersResultEntity(
            paginationOffset = input.data.offset,
            currentCount = input.data.count,
            totalCount = input.data.total,
            code = input.code,
            status = input.status,
            characters = input.data.results.map { responseCharacterToResponseEntity(it) }
        )
    }

    private fun responseCharacterToResponseEntity(character: Character) : CharacterEntity {

        val path = "${character.thumbnail.path}.${character.thumbnail.extension}"
        val url = path.replaceFirst("http", "https")

        return CharacterEntity(
            name = character.name,
            imageUrl = url
        )
    }
}