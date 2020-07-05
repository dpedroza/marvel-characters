package com.marvel.domain.characters.usecase

import com.marvel.domain.characters.params.GetCharactersParams
import com.marvel.domain.characters.model.GetCharactersResult
import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.core.UseCase
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
