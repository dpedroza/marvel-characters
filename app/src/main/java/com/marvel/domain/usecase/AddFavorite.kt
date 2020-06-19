package com.marvel.domain.usecase

import com.marvel.domain.core.UseCase
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.repository.FavoriteRepository
import io.reactivex.Completable
import javax.inject.Inject

class AddFavorite @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase.FromCompletable.WithInput<CharacterEntity> {

    override fun execute(params: CharacterEntity): Completable {
        return repository.insert(params)
    }

}