package com.marvel.domain.characters.usecase

import com.marvel.domain.UseCase
import com.marvel.domain.characters.model.params.GetCharactersParams
import com.marvel.domain.characters.model.result.GetCharactersResult
import com.marvel.domain.characters.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharactersRepository
) : UseCase.Single.WithInput<GetCharactersParams, GetCharactersResult> {

    override fun execute(params: GetCharactersParams): Single<GetCharactersResult> {
        return repository.getCharacters(
            offset = params.offset,
            query = params.query
        )
    }
}
