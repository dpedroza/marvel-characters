package com.marvel.data.favorites.mapper

import com.marvel.data.model.CharacterDto
import com.marvel.domain.model.CharacterEntity

interface DatabaseMapper {
    fun toEntityList(list: List<CharacterDto>): List<CharacterEntity>
    fun toEntity(dto: CharacterDto): CharacterEntity
    fun toDataObjectList(list: List<CharacterEntity>): List<CharacterDto>
    fun toDto(entity: CharacterEntity): CharacterDto
}
