package com.marvel.data.local.mapper

import com.marvel.data.model.FavoriteCharacterDataObject
import com.marvel.domain.model.CharacterEntity

class DatabaseMapper {

    fun toEntityList(list: List<FavoriteCharacterDataObject>): List<CharacterEntity> {
        return list.map { toEntity(it) }
    }

    private fun toEntity(dataObject: FavoriteCharacterDataObject): CharacterEntity {
        return CharacterEntity(
            id = dataObject.id,
            name = dataObject.name,
            imageUrl = dataObject.photoUrl,
            isFavorite = true
        )
    }

    fun toDataObjectList(list: List<CharacterEntity>): List<FavoriteCharacterDataObject> {
        return list.map { toDataObject(it) }
    }

    fun toDataObject(entity: CharacterEntity): FavoriteCharacterDataObject {
        return FavoriteCharacterDataObject(
            id = entity.id,
            name = entity.name,
            photoUrl = entity.imageUrl
        )
    }
}