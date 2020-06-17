package com.marvel.domain

import com.marvel.data.model.GetCharacthersResponse
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun loadCharacters(): Single<GetCharacthersResponse>
}