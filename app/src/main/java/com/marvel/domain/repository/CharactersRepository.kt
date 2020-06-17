package com.marvel.domain.repository

import com.marvel.data.model.CharactersResponse
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun loadCharacters(
        limit: Int,
        offset: Int,
        nameStartsWith: String
    ): Single<CharactersResponse>
}