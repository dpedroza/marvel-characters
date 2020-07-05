package com.marvel.presentation.mapper

import com.marvel.domain.core.CharacterEntity
import com.marvel.presentation.model.CharacterViewObject

class ViewObjectMapper {

    fun toViewObjectList(input: List<CharacterEntity>): List<CharacterViewObject> {
        return input.map { toViewObject(it) }
    }

    private fun toViewObject(character: CharacterEntity): CharacterViewObject {
        return CharacterViewObject(
            id = character.id,
            name = character.name,
            bannerURL = character.imageUrl,
            isFavorite = character.isFavorite,
            description = character.description,
            comics = character.comics,
            series = character.series
        )
    }

    fun toEntity(character: CharacterViewObject): CharacterEntity {
        return CharacterEntity(
            id = character.id,
            name = character.name,
            imageUrl = character.bannerURL,
            isFavorite = character.isFavorite,
            description = character.description,
            comics = character.comics,
            series = character.series
        )
    }
}
