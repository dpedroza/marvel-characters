package com.marvel.domain.usecase

import com.marvel.data.model.CharactersResponse
import com.marvel.domain.core.UseCase
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharactersRepository
) : UseCase<CharactersResponse>() {

    private companion object {
        private const val LIMIT = 20
    }

    override fun buildCase(query: String, offset: Int): Single<CharactersResponse> {
        return repository.loadCharacters(limit = LIMIT, offset = offset, nameStartsWith = query)
    }
}