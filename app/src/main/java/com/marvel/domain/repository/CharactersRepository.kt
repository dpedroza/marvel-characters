package com.marvel.domain.repository

import com.marvel.domain.model.GetCharactersResultEntity
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun loadCharacters(offset: Int, nameStartsWith: String): Single<GetCharactersResultEntity>
}