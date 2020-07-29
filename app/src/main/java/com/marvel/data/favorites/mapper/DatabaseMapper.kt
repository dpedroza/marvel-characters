package com.marvel.data.favorites.mapper

import com.marvel.data.favorites.model.CharacterDto
import com.marvel.domain.characters.model.entity.CharacterEntity

class DatabaseMapper {

    fun toEntityList(list: List<CharacterDto>): List<CharacterEntity> {
        return list.map { toEntity(it) }
    }

    private fun toEntity(dto: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.photoUrl,
            isFavorite = true
        )
    }

    fun toDtoList(list: List<CharacterEntity>): List<CharacterDto> {
        return list.map { toDto(it) }
    }

    fun toDto(entity: CharacterEntity): CharacterDto {
        return CharacterDto(
            id = entity.id,
            name = entity.name,
            photoUrl = entity.imageUrl
        )
    }
}
