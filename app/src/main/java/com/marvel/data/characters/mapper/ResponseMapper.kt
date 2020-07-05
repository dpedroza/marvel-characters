package com.marvel.data.characters.mapper

import com.marvel.data.model.api.Item
import com.marvel.data.model.api.CharacterRemoteObject
import com.marvel.data.model.api.ComicsRemoteObject
import com.marvel.data.model.api.SeriesRemoteObject
import com.marvel.data.model.api.GetCharactersApiResponse
import com.marvel.data.model.api.GetComicsApiResponse
import com.marvel.data.model.api.GetSeriesApiResponse
import com.marvel.domain.model.entity.CharacterEntity
import com.marvel.domain.model.entity.ComicsEntity
import com.marvel.domain.model.entity.SeriesEntity
import com.marvel.domain.model.result.GetCharactersResult
import com.marvel.domain.model.result.GetComicsResult
import com.marvel.domain.model.result.GetSeriesResult

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
            isFavorite = isFavorite,
            comics = characterRemoteObject.comics.items.map { toComicsEntity(it) },
            series = characterRemoteObject.series.items.map { toSeriesEntity(it) }
        )
    }

    private fun toComicsEntity(
        series: Item
    ): ComicsEntity {
        return ComicsEntity(
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

    private fun toComicsEntity(
        comics: ComicsRemoteObject
    ): ComicsEntity {
        return ComicsEntity(
            name = comics.title,
            url = comics.resourceURI
        )
    }

    private fun toSeriesEntity(
        series: SeriesRemoteObject
    ): SeriesEntity {
        return SeriesEntity(
            name = series.title,
            url = series.resourceURI
        )
    }

    private fun buildImagePath(path: String, extension: String): String {
        val imagePath = "$path.$extension"
        return imagePath.replace("http", "https")
    }
}
