package com.marvel.data.favorites.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marvel.data.favorites.model.CharacterDto

@Database(entities = [(CharacterDto::class)], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
