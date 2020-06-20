package com.marvel.data.remote

import com.marvel.data.cryptography.Hash
import com.marvel.data.local.database.FavoriteDatabase
import com.marvel.data.remote.mapper.ResponseMapper
import com.marvel.data.remote.service.MarvelApiService
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val database: FavoriteDatabase,
    private val service: MarvelApiService
) : CharactersRepository {

    private val mapper = ResponseMapper()

    override fun getCharacters(
        offset: Int,
        nameStartsWith: String?
    ): Single<GetCharactersResultEntity> {

        val timestamp = System.currentTimeMillis().toString()
        val publicKey = "88f86348ba02122d3d0f54cf829cf0d9"
        val privateKey = "d5b598cf48a7dbc8ae0539debce6408323ec3cd4"
        val hash = Hash.generateMD5(timestamp, publicKey, privateKey)
        val localFavorites = database.favoriteDao().getFavoritesIds()
        return service.getCharacters(
            apikey = publicKey,
            timestamp = timestamp,
            hash = hash,
            offset = offset,
            nameStartsWith = nameStartsWith
        ).map { response ->
            mapper.toEntityList(localFavorites, response)
        }

    }
}
