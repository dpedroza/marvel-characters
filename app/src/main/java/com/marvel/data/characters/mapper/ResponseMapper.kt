package com.marvel.data.characters.mapper

import com.marvel.data.model.CharacterRemoteObject
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
        characterRemoteObject: CharacterRemoteObject,
        isFavorite: Boolean
    ): CharacterEntity {
        return CharacterEntity(
            id = characterRemoteObject.id,
            name = characterRemoteObject.name,
            imageUrl = buildImagePath(characterRemoteObject),
            isFavorite = isFavorite
        )
    }

    private fun buildImagePath(characterRemoteObject: CharacterRemoteObject): String {
        return "${characterRemoteObject.thumbnail.path}.${characterRemoteObject.thumbnail.extension}"
            .replaceFirst(
                "http",
                "https"
            )
    }
}
