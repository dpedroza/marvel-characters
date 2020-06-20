package com.marvel.data.favorites.mapper

import com.marvel.data.model.FavoriteCharacterDto
import com.marvel.domain.model.CharacterEntity

interface DatabaseMapper {
    fun toEntityList(list: List<FavoriteCharacterDto>): List<CharacterEntity>
    fun toEntity(dto: FavoriteCharacterDto): CharacterEntity
    fun toDataObjectList(list: List<CharacterEntity>): List<FavoriteCharacterDto>
    fun toDataObject(entity: CharacterEntity): FavoriteCharacterDto
}
