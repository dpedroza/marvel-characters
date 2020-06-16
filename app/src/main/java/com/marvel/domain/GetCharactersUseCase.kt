package com.marvel.domain

import com.marvel.data.model.GetCharacthersResponse
import io.reactivex.rxjava3.core.Single

interface GetCharactersUseCase {

    fun getCharacters(): Single<GetCharacthersResponse>
}