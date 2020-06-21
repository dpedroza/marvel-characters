package com.marvel.presentation.mapper

import com.marvel.domain.model.CharacterEntity
import com.marvel.presentation.model.CharacterViewObject

interface ViewObjectMapper {

    fun toViewObjectList(input: List<CharacterEntity>): List<CharacterViewObject>
    fun toViewObject(character: CharacterEntity): CharacterViewObject
    fun toEntity(character: CharacterViewObject): CharacterEntity
}