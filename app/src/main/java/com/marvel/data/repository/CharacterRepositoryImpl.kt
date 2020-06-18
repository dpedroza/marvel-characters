package com.marvel.data.repository

import com.marvel.data.cryptography.Hash
import com.marvel.data.mapper.ResponseMapper
import com.marvel.data.service.MarvelApiService
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: MarvelApiService,
    private val mapper: ResponseMapper
) : CharactersRepository {

    override fun loadCharacters(
        offset: Int,
        nameStartsWith: String
    ): Single<GetCharactersResultEntity> {

        val timestamp = System.currentTimeMillis().toString()
        val publicKey = "88f86348ba02122d3d0f54cf829cf0d9"
        val privateKey = "d5b598cf48a7dbc8ae0539debce6408323ec3cd4"
        val hash = Hash.generateMD5(timestamp, publicKey, privateKey)
        return service.getCharacters(
            apikey = publicKey,
            timestamp = timestamp,
            hash = hash,
            offset = offset
        ).map { mapper.transform(it) }
    }
}