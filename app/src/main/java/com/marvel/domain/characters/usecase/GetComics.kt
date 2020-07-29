package com.marvel.domain.characters.usecase

import com.marvel.domain.UseCase
import com.marvel.domain.characters.model.result.GetComicsResult
import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.characters.model.params.GetComicsParams
import io.reactivex.Single
import javax.inject.Inject

class GetComics @Inject constructor(
    val repository: CharactersRepository
) : UseCase.FromSingle.WithInput<GetComicsParams, GetComicsResult> {

    override fun execute(params: GetComicsParams): Single<GetComicsResult> {
        return repository.getComics(
            offset = params.offset,
            characterId = params.characterId
        )
    }
}
