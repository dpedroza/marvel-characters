package com.marvel.data.database

import androidx.room.*
import com.marvel.data.model.FavoriteCharacterDataTransformationObject
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM ${FavoriteCharacterDataTransformationObject.TABLE} ORDER BY name ASC")
    fun getFavorites(): Single<List<FavoriteCharacterDataTransformationObject>>

    @Query("SELECT id FROM ${FavoriteCharacterDataTransformationObject.TABLE}")
    fun getFavoritesIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: FavoriteCharacterDataTransformationObject): Completable

    @Delete
    fun delete(favorite: FavoriteCharacterDataTransformationObject): Completable
}