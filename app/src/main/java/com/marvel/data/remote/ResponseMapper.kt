package com.marvel.data.remote

import com.marvel.data.model.Character
import com.marvel.data.model.MarvelServiceApiResponse
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersResultEntity

class ResponseMapper {

    fun transform(
        remoteInput: MarvelServiceApiResponse,
        localInput: List<Int>
    ): GetCharactersResultEntity {

        return GetCharactersResultEntity(
            paginationOffset = remoteInput.data.offset,
            currentCount = remoteInput.data.count,
            totalCount = remoteInput.data.total,
            code = remoteInput.code,
            status = remoteInput.status,
            characters = remoteInput.data.results.map {
                val isFavorite = localInput.contains(it.id)
                responseCharacterToResponseEntity(
                    it,
                    isFavorite
                )
            }
        )
    }

    private fun responseCharacterToResponseEntity(
        character: Character,
        isFavorite: Boolean
    ): CharacterEntity {

        val path = "${character.thumbnail.path}.${character.thumbnail.extension}"
        val url = path.replaceFirst("http", "https")

        return CharacterEntity(
            id = character.id,
            name = character.name,
            imageUrl = url,
            isFavorite = isFavorite
        )
    }
}
