package com.marvel.data.favorites.mapper

import com.marvel.data.model.FavoriteCharacterDto
import com.marvel.domain.model.CharacterEntity

class DatabaseMapperImpl: DatabaseMapper {

    override fun toEntityList(list: List<FavoriteCharacterDto>): List<CharacterEntity> {
        return list.map { toEntity(it) }
    }

    override fun toEntity(dto: FavoriteCharacterDto): CharacterEntity {
        return CharacterEntity(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.photoUrl,
            isFavorite = true
        )
    }

    override fun toDataObjectList(list: List<CharacterEntity>): List<FavoriteCharacterDto> {
        return list.map { toDataObject(it) }
    }

    override fun toDataObject(entity: CharacterEntity): FavoriteCharacterDto {
        return FavoriteCharacterDto(
            id = entity.id,
            name = entity.name,
            photoUrl = entity.imageUrl
        )
    }
}