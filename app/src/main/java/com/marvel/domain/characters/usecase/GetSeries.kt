package com.marvel.domain.characters.usecase

import com.marvel.domain.characters.model.GetSeriesResult
import com.marvel.domain.characters.CharactersRepository
import com.marvel.domain.characters.params.GetSeriesParams
import com.marvel.domain.core.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetSeries @Inject constructor(
    val repository: CharactersRepository
) : UseCase.FromSingle.WithInput<GetSeriesParams, GetSeriesResult> {

    override fun execute(params: GetSeriesParams): Single<GetSeriesResult> {
        return repository.getSeries(
            offset = params.offset,
            characterId = params.characterId
        )
    }
}
