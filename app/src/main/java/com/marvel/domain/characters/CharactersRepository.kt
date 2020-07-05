package com.marvel.domain.characters

import com.marvel.domain.characters.model.GetCharactersResult
import com.marvel.domain.characters.model.GetComicsResult
import com.marvel.domain.characters.model.GetSeriesResult
import io.reactivex.Single

interface CharactersRepository {

    fun getCharacters(offset: Int, nameStartsWith: String?): Single<GetCharactersResult>
    fun getSeries(offset: Int, characterId: Int): Single<GetSeriesResult>
    fun getComics(offset: Int, characterId: Int): Single<GetComicsResult>
}
