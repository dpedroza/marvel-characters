package com.marvel.data.favorites.mapper

import com.marvel.data.model.CharacterDto
import com.marvel.domain.model.CharacterEntity

class DatabaseMapperImpl: DatabaseMapper {

    override fun toEntityList(list: List<CharacterDto>): List<CharacterEntity> {
        return list.map { toEntity(it) }
    }

    override fun toEntity(dto: CharacterDto): CharacterEntity {
        return CharacterEntity(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.photoUrl,
            isFavorite = true
        )
    }

    override fun toDataObjectList(list: List<CharacterEntity>): List<CharacterDto> {
        return list.map { toDto(it) }
    }

    override fun toDto(entity: CharacterEntity): CharacterDto {
        return CharacterDto(
            id = entity.id,
            name = entity.name,
            photoUrl = entity.imageUrl
        )
    }
}