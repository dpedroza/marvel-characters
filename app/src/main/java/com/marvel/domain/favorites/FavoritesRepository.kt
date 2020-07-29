package com.marvel.domain.favorites

import com.marvel.domain.characters.model.entity.CharacterEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FavoritesRepository {

    fun getFavorites(): Single<List<CharacterEntity>>
    fun insert(favorite: CharacterEntity): Completable
    fun delete(favorite: CharacterEntity): Completable
}
