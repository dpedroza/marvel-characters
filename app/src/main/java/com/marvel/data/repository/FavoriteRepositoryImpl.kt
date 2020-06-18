package com.marvel.data.repository

import com.marvel.data.local.FavoriteDatabase
import com.marvel.data.mapper.DatabaseMapper
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.repository.FavoriteRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val database: FavoriteDatabase
) : FavoriteRepository {

    private val mapper = DatabaseMapper()

    override fun getFavorites(): Single<List<CharacterEntity>> {
        return database.favoriteDao().getFavorites().map { mapper.transformList(it) }
    }

    override fun insert(favorite: CharacterEntity): Completable {
        return database.favoriteDao().insert(mapper.toDto(favorite))
    }

    override fun delete(favorite: CharacterEntity): Completable {
        return database.favoriteDao().delete(mapper.toDto(favorite))
    }
}
