package com.marvel.domain.usecase

import com.marvel.domain.model.GetCharactersParameters
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharactersRepository
) : UseCase.FromSingle.WithInput<GetCharactersParameters, GetCharactersResultEntity> {

    override fun execute(params: GetCharactersParameters): Single<GetCharactersResultEntity> {
        return repository.getCharacters(
            offset = params.offset,
            nameStartsWith = params.query
        )
    }
}
