package com.marvel.domain.favorites.usecase

import com.marvel.domain.characters.model.entity.CharacterEntity
import com.marvel.domain.favorites.FavoritesRepository
import com.marvel.domain.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val repository: FavoritesRepository
) : UseCase.FromSingle.WithoutInput<List<CharacterEntity>> {

    override fun execute(): Single<List<CharacterEntity>> {
        return repository.getFavorites()
    }
}
