package com.marvel.domain.favorites.usecase

import com.marvel.domain.core.CharacterEntity
import com.marvel.domain.favorites.FavoriteRepository
import com.marvel.domain.core.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase.FromSingle.WithoutInput<List<CharacterEntity>> {

    override fun execute(): Single<List<CharacterEntity>> {
        return repository.getFavorites()
    }
}
