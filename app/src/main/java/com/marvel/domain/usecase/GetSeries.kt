package com.marvel.domain.usecase

import com.marvel.domain.model.result.GetSeriesResult
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSeries @Inject constructor(
    val repository: CharactersRepository
) : UseCase.FromSingle.WithInput<Int, GetSeriesResult> {

    override fun execute(params: Int): Single<GetSeriesResult> {
        return repository.getSeries(params, params)
    }
}
