package com.marvel.domain.characters

import com.marvel.domain.characters.model.result.GetCharactersResult
import com.marvel.domain.characters.model.result.GetComicsResult
import com.marvel.domain.characters.model.result.GetSeriesResult
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(query: String?, offset: Int): Single<GetCharactersResult>
    fun getSeries(characterId: Int, offset: Int): Single<GetSeriesResult>
    fun getComics(characterId: Int, offset: Int): Single<GetComicsResult>
}
