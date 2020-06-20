package com.marvel.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteCharacterDataTransformationObject.TABLE)
data class FavoriteCharacterDataTransformationObject(
    @PrimaryKey val id: Int,
    val name: String,
    val photoUrl: String
) {
    companion object {
        const val TABLE = "favorites"
    }
}