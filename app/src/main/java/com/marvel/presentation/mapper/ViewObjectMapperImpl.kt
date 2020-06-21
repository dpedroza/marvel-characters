package com.marvel.presentation.mapper

import com.marvel.domain.model.CharacterEntity
import com.marvel.presentation.model.CharacterViewObject

class ViewObjectMapperImpl : ViewObjectMapper{

    override fun toViewObjectList(input: List<CharacterEntity>): List<CharacterViewObject> {
        return input.map { toViewObject(it) }
    }

    override fun toViewObject(character: CharacterEntity): CharacterViewObject {
        return CharacterViewObject(
            id = character.id,
            name = character.name,
            bannerURL = character.imageUrl,
            isFavorite = character.isFavorite
        )
    }

    override fun toEntity(character: CharacterViewObject): CharacterEntity {
        return CharacterEntity(
            id = character.id,
            name = character.name,
            imageUrl = character.bannerURL,
            isFavorite = character.isFavorite
        )
    }
}