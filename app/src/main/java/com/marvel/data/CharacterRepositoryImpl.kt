package com.marvel.data

import com.marvel.data.model.GetCharacthersResponse
import com.marvel.domain.CharactersRepository
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(val service: MarvelApiService) :
    CharactersRepository {

    override fun loadCharacters(): Single<GetCharacthersResponse> {
        return service.getCharacters("", "", Date(), "", 0, 0, 0, "name", 20, 1)
    }
}