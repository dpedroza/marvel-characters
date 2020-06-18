package com.marvel.domain.usecase

import com.marvel.domain.core.UseCase
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.repository.FavoriteRepository
import io.reactivex.Single

import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val repository: FavoriteRepository
) : UseCase<List<CharacterEntity>>() {

    override fun buildCase(query: String, offset: Int): Single<List<CharacterEntity>> {
        return repository.getFavorites()
    }
}