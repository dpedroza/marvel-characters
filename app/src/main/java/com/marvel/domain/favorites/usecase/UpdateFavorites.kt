package com.marvel.domain.favorites.usecase

import com.marvel.domain.characters.model.entity.CharacterEntity
import com.marvel.domain.favorites.FavoritesRepository
import com.marvel.domain.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateFavorites @Inject constructor(
    private val repository: FavoritesRepository
) : UseCase.FromCompletable.WithInput<CharacterEntity> {

    override fun execute(params: CharacterEntity): Completable {
        return if (params.isFavorite) {
            repository.insert(params)
        } else {
            repository.delete(params)
        }
    }
}
