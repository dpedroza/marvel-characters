package com.marvel.data.characters.mapper

import com.marvel.data.characters.model.characters.CharacterRemoteObject
import com.marvel.data.characters.model.characters.GetCharactersApiResponse
import com.marvel.data.characters.model.comics.ComicsRemoteObject
import com.marvel.data.characters.model.comics.GetComicsApiResponse
import com.marvel.data.characters.model.series.GetSeriesApiResponse
import com.marvel.data.characters.model.series.SeriesRemoteObject
import com.marvel.domain.characters.model.entity.ComicsEntity
import com.marvel.domain.characters.model.entity.SeriesEntity
import com.marvel.domain.characters.model.result.GetCharactersResult
import com.marvel.domain.characters.model.result.GetComicsResult
import com.marvel.domain.characters.model.result.GetSeriesResult
import com.marvel.domain.characters.model.entity.CharacterEntity

class ResponseMapper {

    fun toCharacterEntityList(
        localFavorites: List<Int>,
        remoteCharacters: GetCharactersApiResponse
    ): GetCharactersResult {

        return GetCharactersResult(
            paginationOffset = remoteCharacters.data.offset,
            currentCount = remoteCharacters.data.count,
            totalCount = remoteCharacters.data.total,
            code = remoteCharacters.code,
            status = remoteCharacters.status,
            characters = remoteCharacters.data.results.map { character ->

                /**
                 * Check if a character is persisted as favorite to local database
                 * Set it isFavorite value accordingly
                 */

                /**
                 * Check if a character is persisted as favorite to local database
                 * Set it isFavorite value accordingly
                 */

                val isFavorite = localFavorites.contains(character.id)
                toCharacterEntity(character, isFavorite)
            }
        )
    }

    fun toSeriesEntityList(
        remoteSeries: GetSeriesApiResponse
    ): GetSeriesResult {

        return GetSeriesResult(
            paginationOffset = remoteSeries.data.offset,
            currentCount = remoteSeries.data.count,
            totalCount = remoteSeries.data.total,
            code = remoteSeries.code,
            status = remoteSeries.status,
            series = remoteSeries.data.results.map { toSeriesEntity(it) }
        )
    }

    fun toComicsEntityList(
        remoteSeries: GetComicsApiResponse
    ): GetComicsResult {
        return GetComicsResult(
            paginationOffset = remoteSeries.data.offset,
            currentCount = remoteSeries.data.count,
            totalCount = remoteSeries.data.total,
            code = remoteSeries.code,
            status = remoteSeries.status,
            comics = remoteSeries.data.results.map { toComicsEntity(it) }
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
            imageUrl = buildImagePath(
                path = characterRemoteObject.thumbnail.path,
                extension = characterRemoteObject.thumbnail.extension
            ),
            isFavorite = isFavorite
        )
    }

    private fun toComicsEntity(
        comics: ComicsRemoteObject
    ): ComicsEntity {
        return ComicsEntity(
            name = comics.title,
            imageUrl = buildImagePath(
                path = comics.thumbnail.path,
                extension = comics.thumbnail.extension
            )
        )
    }

    private fun toSeriesEntity(
        series: SeriesRemoteObject
    ): SeriesEntity {
        return SeriesEntity(
            name = series.title,
            imageUrl = buildImagePath(
                path = series.thumbnail.path,
                extension = series.thumbnail.extension
            )
        )
    }

    private fun buildImagePath(path: String, extension: String): String {
        val imagePath = "$path.$extension"
        return imagePath.replace("http", "https")
    }
}
