package com.marvel.data.favorites.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.marvel.data.favorites.model.CharacterDto
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${FavoriteDatabase.NAME} ORDER BY name ASC")
    fun getFavorites(): Single<List<CharacterDto>>

    @Query("SELECT id FROM ${FavoriteDatabase.NAME}")
    fun getFavoritesIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: CharacterDto): Completable

    @Delete
    fun delete(favorite: CharacterDto): Completable
}
