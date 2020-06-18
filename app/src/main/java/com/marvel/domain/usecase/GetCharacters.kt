package com.marvel.domain.usecase

import com.marvel.domain.core.UseCase
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharactersRepository
) : UseCase<GetCharactersResultEntity>() {

    override fun buildCase(query: String, offset: Int): Single<GetCharactersResultEntity> {
        return repository.getCharacters(offset = offset, nameStartsWith = query)
    }
}