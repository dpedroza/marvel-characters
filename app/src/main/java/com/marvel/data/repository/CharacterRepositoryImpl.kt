package com.marvel.data.repository

import com.marvel.data.cryptography.Hash
import com.marvel.data.model.CharactersResponse
import com.marvel.data.service.MarvelApiService
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: MarvelApiService
) : CharactersRepository {

    override fun loadCharacters(
        limit: Int,
        offset: Int,
        nameStartsWith: String
    ): Single<CharactersResponse> {
        val timestamp = System.currentTimeMillis().toString()
        val publicKey = "88f86348ba02122d3d0f54cf829cf0d9"
        val privateKey = "d5b598cf48a7dbc8ae0539debce6408323ec3cd4"
        val hash = Hash.generateMD5(timestamp, publicKey, privateKey)
        return service.getCharacters(publicKey, timestamp, hash)
    }
}
