package com.marvel.data.characters.repository

import com.marvel.data.characters.mapper.ResponseMapper
import com.marvel.data.characters.service.ApiService
import com.marvel.data.favorites.database.FavoriteDao
import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.characters.model.result.GetCharactersResult
import com.marvel.domain.characters.model.result.GetComicsResult
import com.marvel.domain.characters.model.result.GetSeriesResult
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val service: ApiService
) : CharactersRepository {
    private val mapper = ResponseMapper()

    override fun getCharacters(
        query: String?,
        offset: Int
    ): Single<GetCharactersResult> {
        val localFavorites = dao.getFavoritesIds()
        return service.getCharacters(query, offset)
            .map { remoteCharacters ->
                mapper.toCharacterEntityList(localFavorites, remoteCharacters)
            }
    }

    override fun getSeries(
        characterId: Int,
        offset: Int
    ): Single<GetSeriesResult> {
        return service.getSeries(characterId, offset)
            .map { mapper.toSeriesEntityList(it) }
    }

    override fun getComics(
        characterId: Int,
        offset: Int
    ): Single<GetComicsResult> {
        return service.getComics(characterId, offset)
            .map {
                mapper.toComicsEntityList(it)
            }
    }
}
