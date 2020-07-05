package com.marvel.domain.usecase

import com.marvel.domain.model.params.GetCharactersParams
import com.marvel.domain.model.result.GetCharactersResult
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharactersRepository
) : UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResult> {

    override fun execute(params: GetCharactersParams): Single<GetCharactersResult> {
        return repository.getCharacters(
            offset = params.offset,
            nameStartsWith = params.query
        )
    }
}
