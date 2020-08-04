package com.marvel.data.favorites.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marvel.data.favorites.database.FavoriteDatabase

@Entity(tableName = FavoriteDatabase.NAME)
data class CharacterDto(
    @PrimaryKey val id: Int,
    val name: String,
    val photoUrl: String
)
