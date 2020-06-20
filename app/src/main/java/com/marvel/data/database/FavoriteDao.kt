package com.marvel.data.database

import androidx.room.*
import com.marvel.data.model.FavoriteCharacterDto
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${FavoriteCharacterDto.TABLE} ORDER BY name ASC")
    fun getFavorites(): Single<List<FavoriteCharacterDto>>

    @Query("SELECT id FROM ${FavoriteCharacterDto.TABLE}")
    fun getFavoritesIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteCharacterDto): Completable

    @Delete
    fun delete(favorite: FavoriteCharacterDto): Completable
}