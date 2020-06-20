package com.marvel.data.remote

import com.marvel.BuildConfig
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
        val localFavorites = database.favoriteDao().getFavoritesIds()
        return service.getCharacters(apiKey, timestamp, hash, offset, nameStartsWith)
            .map { response ->
                mapper.toEntityList(localFavorites, response)
            }
    }
}
