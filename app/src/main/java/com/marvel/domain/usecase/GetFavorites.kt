package com.marvel.domain.usecase

import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.repository.FavoriteRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase.FromSingle.WithoutInput<List<CharacterEntity>> {

    override fun execute(): Single<List<CharacterEntity>> {
        return repository.getFavorites()
    }
}