package com.marvel.data.mapper

import com.marvel.data.model.Character
import com.marvel.presentation.model.CharacterViewObject

class CharacterMapper :
    Mapper<Character, CharacterViewObject> {

    override fun transform(input: Character): CharacterViewObject =
            CharacterViewObject(
                    input.name,
                    input.resourceURI)

    override fun transformList(inputList: List<Character>): List<CharacterViewObject> {
        return inputList.map { transform(it) }
    }

}