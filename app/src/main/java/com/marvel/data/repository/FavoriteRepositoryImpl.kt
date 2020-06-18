package com.marvel.data.repository

import com.marvel.data.local.FavoriteDatabase
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.repository.FavoriteRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val database: FavoriteDatabase
) : FavoriteRepository {

    override fun getFavorites(): Single<List<CharacterEntity>> {
        return database.favoriteDao().getFavorites().map {
            val result = mutableListOf<CharacterEntity>()
            it.forEach { favoriteDto ->
                result.add(
                    CharacterEntity(
                        id = favoriteDto.id,
                        name = favoriteDto.name,
                        imageUrl = favoriteDto.photoUrl,
                        isFavorite = true
                    )
                )
            }
            result
        }
    }

    override fun insert(favorite: CharacterEntity): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(favorite: CharacterEntity): Completable {
        TODO("Not yet implemented")
    }

}
