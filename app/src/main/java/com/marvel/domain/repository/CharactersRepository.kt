package com.marvel.domain.repository

import com.marvel.domain.model.result.GetCharactersResult
import com.marvel.domain.model.result.GetComicsResult
import com.marvel.domain.model.result.GetSeriesResult
import io.reactivex.Single

interface CharactersRepository {

    fun getCharacters(offset: Int, nameStartsWith: String?): Single<GetCharactersResult>
    fun getSeries(offset: Int, characterId: Int): Single<GetSeriesResult>
    fun getComics(offset: Int, characterId: Int): Single<GetComicsResult>
}
