package com.marvel.data.favorites.mapper

import com.marvel.data.model.FavoriteCharacterDataTransformationObject
import com.marvel.domain.model.CharacterEntity

interface DatabaseMapper {
    fun toEntityList(list: List<FavoriteCharacterDataTransformationObject>): List<CharacterEntity>
    fun toEntity(dataTransformationObject: FavoriteCharacterDataTransformationObject): CharacterEntity
    fun toDataObjectList(list: List<CharacterEntity>): List<FavoriteCharacterDataTransformationObject>
    fun toDataObject(entity: CharacterEntity): FavoriteCharacterDataTransformationObject
}
