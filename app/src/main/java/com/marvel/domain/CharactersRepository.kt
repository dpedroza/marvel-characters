package com.marvel.domain

import com.marvel.data.model.CharactersResponse
import com.marvel.presentation.model.CharacterViewObject
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun loadCharacters(): Single<CharactersResponse>
}