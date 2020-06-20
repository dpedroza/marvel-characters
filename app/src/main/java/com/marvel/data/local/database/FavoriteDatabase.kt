package com.marvel.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marvel.data.model.FavoriteCharacterDataTransformationObject

@Database(entities = [(FavoriteCharacterDataTransformationObject::class)], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDataAccessObject
}