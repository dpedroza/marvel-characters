package com.marvel.data.local

import androidx.room.*
import com.marvel.data.model.FavoriteCharacterDto
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${FavoriteCharacterDto.TABLE}")
    fun getFavorites(): Single<List<FavoriteCharacterDto>>

    @Query("SELECT id FROM ${FavoriteCharacterDto.TABLE}")
    fun getFavoritesIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteCharacterDto)

    @Delete
    fun delete(favorite: FavoriteCharacterDto)
}