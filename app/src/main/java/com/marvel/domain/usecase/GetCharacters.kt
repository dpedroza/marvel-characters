package com.marvel.domain.usecase

import com.marvel.domain.core.UseCase
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharactersRepository
) : UseCase<GetCharactersResultEntity>() {

    private companion object {
        private const val LIMIT = 20
    }

    override fun buildCase(query: String, offset: Int): Single<GetCharactersResultEntity> {
        return repository.loadCharacters(offset = offset, nameStartsWith = query)
    }
}