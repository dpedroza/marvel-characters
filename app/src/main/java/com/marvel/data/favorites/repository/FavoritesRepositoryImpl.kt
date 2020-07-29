package com.marvel.data.favorites.repository

import com.marvel.data.favorites.database.FavoriteDao
import com.marvel.data.favorites.mapper.DatabaseMapper
import com.marvel.domain.characters.model.entity.CharacterEntity
import com.marvel.domain.favorites.FavoritesRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoritesRepository {

    private val mapper = DatabaseMapper()

    override fun getFavorites(): Single<List<CharacterEntity>> {
        return dao.getFavorites().map { mapper.toEntityList(it) }
    }

    override fun insert(favorite: CharacterEntity): Completable {
        return dao.insert(mapper.toDto(favorite))
    }

    override fun delete(favorite: CharacterEntity): Completable {
        return dao.delete(mapper.toDto(favorite))
    }
}
