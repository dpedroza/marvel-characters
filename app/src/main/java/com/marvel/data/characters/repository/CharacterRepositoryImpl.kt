package com.marvel.data.characters.repository

import com.marvel.BuildConfig
import com.marvel.data.characters.mapper.ResponseMapper
import com.marvel.data.characters.service.MarvelApiService
import com.marvel.data.characters.cryptography.Hash
import com.marvel.data.favorites.database.FavoriteDao
import com.marvel.domain.characters.model.GetCharactersResult
import com.marvel.domain.characters.model.GetComicsResult
import com.marvel.domain.characters.model.GetSeriesResult
import com.marvel.domain.characters.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val service: MarvelApiService
) : CharactersRepository {

    private val mapper = ResponseMapper()

    override fun getCharacters(
        offset: Int,
        nameStartsWith: String?
    ): Single<GetCharactersResult> {
        val timestamp = getTimestamp()
        val apiKey = getApiKey()
        val hash = apiKey.createHash(timestamp)
        val localFavorites = dao.getFavoritesIds()
        return service.getCharacters(apiKey, timestamp, hash, offset, nameStartsWith)
            .map { response ->
                mapper.toCharacterEntityList(localFavorites, response)
            }
    }

    override fun getSeries(offset: Int, characterId: Int): Single<GetSeriesResult> {
        val apiKey = getApiKey()
        val timestamp = getTimestamp()
        val hash = apiKey.createHash(timestamp)
        return service.getSeries(apiKey, timestamp, hash, offset, characterId)
            .map { response ->
                mapper.toSeriesEntityList(response)
            }
    }

    override fun getComics(offset: Int, characterId: Int): Single<GetComicsResult> {
        val timestamp = getTimestamp()
        val apiKey = getApiKey()
        val hash = apiKey.createHash(timestamp)
        return service.getComics(apiKey, timestamp, hash, offset, characterId)
            .map { response ->
                mapper.toComicsEntityList(response)
            }
    }

    private fun getTimestamp() = System.currentTimeMillis().toString()
    private fun getApiKey() = BuildConfig.MARVEL_PUBLIC_KEY

    private fun String.createHash(timestamp: String): String {
        val privateKey = BuildConfig.MARVEL_PRIVATE_KEY
        return Hash.generateMD5(timestamp, this, privateKey)
    }
}
