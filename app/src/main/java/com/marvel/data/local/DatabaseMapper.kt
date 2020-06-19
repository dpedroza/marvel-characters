package com.marvel.data.local

import com.marvel.data.model.FavoriteCharacterDto
import com.marvel.domain.model.CharacterEntity

class DatabaseMapper {

    fun toEntityList(list: List<FavoriteCharacterDto>): List<CharacterEntity> {
        return list.map { toEntity(it) }
    }

    private fun toEntity(dto: FavoriteCharacterDto): CharacterEntity {
        return CharacterEntity(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.photoUrl,
            isFavorite = true
        )
    }

    fun toDtoList(list: List<CharacterEntity>): List<FavoriteCharacterDto> {
        return list.map { toDto(it) }
    }

    fun toDto(entity: CharacterEntity): FavoriteCharacterDto {
        return FavoriteCharacterDto(
            id = entity.id,
            name = entity.name,
            photoUrl = entity.imageUrl
        )
    }
}