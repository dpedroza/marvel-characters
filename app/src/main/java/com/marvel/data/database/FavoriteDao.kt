package com.marvel.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.marvel.data.model.dto.CharacterDto
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
