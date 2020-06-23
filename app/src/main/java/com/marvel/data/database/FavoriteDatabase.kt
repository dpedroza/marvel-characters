package com.marvel.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marvel.data.model.CharacterDto

@Database(entities = [(CharacterDto::class)], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}