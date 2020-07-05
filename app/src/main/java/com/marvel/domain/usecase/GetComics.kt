package com.marvel.domain.usecase

import com.marvel.domain.model.result.GetComicsResult
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetComics @Inject constructor(
    val repository: CharactersRepository
) : UseCase.FromSingle.WithInput<Int, GetComicsResult> {

    override fun execute(params: Int): Single<GetComicsResult> {
        return repository.getComics(params, params)
    }
}
