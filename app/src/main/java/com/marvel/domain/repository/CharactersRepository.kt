package com.marvel.domain.repository

import com.marvel.domain.model.GetCharactersResultEntity
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun getCharacters(offset: Int, nameStartsWith: String): Single<GetCharactersResultEntity>
}