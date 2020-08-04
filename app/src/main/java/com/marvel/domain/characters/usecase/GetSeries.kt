package com.marvel.domain.characters.usecase

import com.marvel.domain.UseCase
import com.marvel.domain.characters.model.result.GetSeriesResult
import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.characters.model.params.GetSeriesParams
import io.reactivex.Single
import javax.inject.Inject

class GetSeries @Inject constructor(
    val repository: CharactersRepository
) : UseCase.Single.WithInput<GetSeriesParams, GetSeriesResult> {

    override fun execute(params: GetSeriesParams): Single<GetSeriesResult> {
        return repository.getSeries(
            offset = params.offset,
            characterId = params.characterId
        )
    }
}
