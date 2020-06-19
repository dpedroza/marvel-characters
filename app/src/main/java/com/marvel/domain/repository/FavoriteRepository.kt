package com.marvel.domain.repository

import com.marvel.domain.model.CharacterEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteRepository {

    fun getFavorites(): Single<List<CharacterEntity>>
    fun insert(favorite: CharacterEntity): Completable
    fun delete(favorite: CharacterEntity): Completable
}