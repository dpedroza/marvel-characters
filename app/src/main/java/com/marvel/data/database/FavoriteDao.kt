package com.marvel.data.database

import androidx.room.*
import com.marvel.data.model.CharacterDto
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${CharacterDto.TABLE} ORDER BY name ASC")
    fun getFavorites(): Single<List<CharacterDto>>

    @Query("SELECT id FROM ${CharacterDto.TABLE}")
    fun getFavoritesIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: CharacterDto): Completable

    @Delete
    fun delete(favorite: CharacterDto): Completable
}