package com.marvel.domain

import com.marvel.data.model.GetCharacthersResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) :
    UseCase<GetCharacthersResponse>() {

    override fun buildCase(): Single<GetCharacthersResponse> {
        return repository.loadCharacters()
    }
}