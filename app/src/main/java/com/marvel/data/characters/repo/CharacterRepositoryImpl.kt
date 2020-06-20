package com.marvel.data.characters.repo

import com.marvel.BuildConfig
import com.marvel.data.cryptography.Hash
import com.marvel.data.database.FavoriteDao
import com.marvel.data.characters.mapper.ResponseMapper
import com.marvel.data.characters.service.MarvelApiService
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val service: MarvelApiService,
    private val mapper: ResponseMapper
) : CharactersRepository {

    override fun getCharacters(
        offset: Int,
        nameStartsWith: String?
    ): Single<GetCharactersResultEntity> {

        val timestamp = System.currentTimeMillis().toString()
        val apiKey = BuildConfig.MARVEL_PUBLIC_KEY
        val privateKey = BuildConfig.MARVEL_PRIVATE_KEY
        val hash = Hash.generateMD5(timestamp, apiKey, privateKey)
        val localFavorites = dao.getFavoritesIds()
        return service.getCharacters(apiKey, timestamp, hash, offset, nameStartsWith)
            .map { response ->
                mapper.toEntityList(localFavorites, response)
            }
    }
}
