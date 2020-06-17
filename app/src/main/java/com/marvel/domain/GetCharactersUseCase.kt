package com.marvel.domain

import com.marvel.data.model.CharactersResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) :
    UseCase<CharactersResponse>() {

    override fun buildCase(): Single<CharactersResponse> {
        return repository.loadCharacters()
    }
}