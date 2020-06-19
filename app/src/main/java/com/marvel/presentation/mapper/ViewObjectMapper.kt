package com.marvel.presentation.mapper

import com.marvel.domain.model.CharacterEntity
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
            isFavorite = character.isFavorite
        )
    }
}
