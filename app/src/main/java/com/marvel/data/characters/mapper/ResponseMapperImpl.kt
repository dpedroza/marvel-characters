package com.marvel.data.characters.mapper

import com.marvel.data.model.CharacterRemoteObject
import com.marvel.data.model.Item
import com.marvel.data.model.MarvelServiceApiResponse
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.ComicEntity
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.model.SeriesEntity

class ResponseMapperImpl : ResponseMapper {

    override fun toEntityList(
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

                /**
                 * Check if a character is persisted as favorite to local database
                 * Set it isFavorite value accordingly
                 *
                 */

                val isFavorite = localFavorites.contains(character.id)
                toCharacterEntity(character, isFavorite)
            }
        )
    }

    private fun toCharacterEntity(
        characterRemoteObject: CharacterRemoteObject,
        isFavorite: Boolean
    ): CharacterEntity {
        return CharacterEntity(
            id = characterRemoteObject.id,
            name = characterRemoteObject.name,
            description = characterRemoteObject.description,
            imageUrl = buildImagePath(characterRemoteObject),
            isFavorite = isFavorite,
            comics = characterRemoteObject.comics.items.map { toComicsEntity(it) },
            series = characterRemoteObject.series.items.map { toSeriesEntity(it) }
        )
    }

    private fun toComicsEntity(
        series: Item
    ): ComicEntity {
        return ComicEntity(
            url = series.resourceURI,
            name = series.name
        )
    }

    private fun toSeriesEntity(
        series: Item
    ): SeriesEntity {
        return SeriesEntity(
            url = series.resourceURI,
            name = series.name
        )
    }

    private fun buildImagePath(characterRemoteObject: CharacterRemoteObject): String {
        return "${characterRemoteObject.thumbnail.path}.${characterRemoteObject.thumbnail.extension}"
            .replaceFirst("http", "https")
    }
}
