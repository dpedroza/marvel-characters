package com.marvel.data.local

import com.marvel.data.local.database.FavoriteDatabase
import com.marvel.data.local.mapper.DatabaseMapper
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.repository.FavoriteRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val database: FavoriteDatabase,
    private val mapper: DatabaseMapper
) : FavoriteRepository {

    override fun getFavorites(): Single<List<CharacterEntity>> {
        return database.favoriteDao().getFavorites().map { mapper.toEntityList(it) }
    }

    override fun insert(favorite: CharacterEntity): Completable {
        return database.favoriteDao().insert(mapper.toDto(favorite))
    }

    override fun delete(favorite: CharacterEntity): Completable {
        return database.favoriteDao().delete(mapper.toDto(favorite))
    }
}