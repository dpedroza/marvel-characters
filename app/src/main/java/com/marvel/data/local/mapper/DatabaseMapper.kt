package com.marvel.data.local.mapper

import com.marvel.data.model.FavoriteCharacterDataTransformationObject
import com.marvel.domain.model.CharacterEntity

class DatabaseMapper {

    fun toEntityList(list: List<FavoriteCharacterDataTransformationObject>): List<CharacterEntity> {
        return list.map { toEntity(it) }
    }

    private fun toEntity(dataTransformationObject: FavoriteCharacterDataTransformationObject): CharacterEntity {
        return CharacterEntity(
            id = dataTransformationObject.id,
            name = dataTransformationObject.name,
            imageUrl = dataTransformationObject.photoUrl,
            isFavorite = true
        )
    }

    fun toDataObjectList(list: List<CharacterEntity>): List<FavoriteCharacterDataTransformationObject> {
        return list.map { toDataObject(it) }
    }

    fun toDataObject(entity: CharacterEntity): FavoriteCharacterDataTransformationObject {
        return FavoriteCharacterDataTransformationObject(
            id = entity.id,
            name = entity.name,
            photoUrl = entity.imageUrl
        )
    }
}