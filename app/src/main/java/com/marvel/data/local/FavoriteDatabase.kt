package com.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marvel.data.model.FavoriteCharacterDto

@Database(entities = [(FavoriteCharacterDto::class)], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}