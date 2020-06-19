package com.marvel.presentation.mapper

import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.presentation.model.CharacterViewObject

class ViewObjectMapper : Mapper<GetCharactersResultEntity, List<CharacterViewObject>> {

    override fun transform(input: GetCharactersResultEntity): List<CharacterViewObject> {
        return input.characters.map { entityCharacterToViewObjectCharacter(it) }
    }

    fun entityCharacterToViewObjectCharacter(character: CharacterEntity): CharacterViewObject {
        return CharacterViewObject(
            id = character.id,
            name = character.name,
            bannerURL = character.imageUrl,
            isFavorite = false
        )
    }
}
