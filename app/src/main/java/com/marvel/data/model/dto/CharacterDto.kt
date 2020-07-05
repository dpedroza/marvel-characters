package com.marvel.data.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CharacterDto.TABLE)
data class CharacterDto(
    @PrimaryKey val id: Int,
    val name: String,
    val photoUrl: String
) {
    companion object {
        const val TABLE = "favorites"
    }
}
