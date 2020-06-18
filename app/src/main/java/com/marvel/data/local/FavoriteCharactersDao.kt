package com.marvel.data.local

import androidx.room.Dao
import com.marvel.data.model.Character

@Dao
interface FavoriteCharactersDao {
    fun getIds() : List<Int>
    fun getCharacters() : List<Character>
}
