package com.marvel.data.local.database

import androidx.room.*
import com.marvel.data.model.FavoriteCharacterDataObject
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${FavoriteCharacterDataObject.TABLE} ORDER BY name ASC")
    fun getFavorites(): Single<List<FavoriteCharacterDataObject>>

    @Query("SELECT id FROM ${FavoriteCharacterDataObject.TABLE}")
    fun getFavoritesIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteCharacterDataObject): Completable

    @Delete
    fun delete(favorite: FavoriteCharacterDataObject): Completable
}