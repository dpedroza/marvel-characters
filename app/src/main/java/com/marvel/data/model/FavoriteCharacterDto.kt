package com.marvel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteCharacterDto.TABLE)
data class FavoriteCharacterDto(
    @PrimaryKey val id: Int,
    val name: String,
    val photoUrl: String
) {
    companion object {
        const val TABLE = "favorites"
    }
}