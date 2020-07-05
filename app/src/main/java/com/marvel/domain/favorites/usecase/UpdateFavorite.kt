package com.marvel.domain.favorites.usecase

import com.marvel.domain.core.CharacterEntity
import com.marvel.domain.favorites.FavoriteRepository
import com.marvel.domain.core.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateFavorite @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase.FromCompletable.WithInput<CharacterEntity> {

    override fun execute(params: CharacterEntity): Completable {
        return if (params.isFavorite) {
            repository.insert(params)
        } else {
            repository.delete(params)
        }
    }
}
