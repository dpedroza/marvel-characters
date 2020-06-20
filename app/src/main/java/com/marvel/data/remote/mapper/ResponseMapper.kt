package com.marvel.data.remote.mapper

import com.marvel.data.model.Character
import com.marvel.data.model.MarvelServiceApiResponse
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersResultEntity

class ResponseMapper {

    fun toEntityList(
        localFavorites: List<Int>,
        remoteCharacters: MarvelServiceApiResponse
    ): GetCharactersResultEntity {

        return GetCharactersResultEntity(
            paginationOffset = remoteCharacters.data.offset,
            currentCount = remoteCharacters.data.count,
            totalCount = remoteCharacters.data.total,
            code = remoteCharacters.code,
            status = remoteCharacters.status,
            characters = remoteCharacters.data.results.map { character ->
                val isFavorite = localFavorites.contains(character.id)
                toEntity(character, isFavorite)
            }
        )
    }

    private fun toEntity(
        character: Character,
        isFavorite: Boolean
    ): CharacterEntity {
        return CharacterEntity(
            id = character.id,
            name = character.name,
            imageUrl = buildImagePath(character),
            isFavorite = isFavorite
        )
    }

    private fun buildImagePath(character: Character): String {
        return "${character.thumbnail.path}.${character.thumbnail.extension}"
            .replaceFirst(
                "http",
                "https"
            )
    }
}
