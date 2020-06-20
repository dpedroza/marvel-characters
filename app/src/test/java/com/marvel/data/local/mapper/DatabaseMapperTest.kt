package com.marvel.data.local.mapper

import com.marvel.data.model.FavoriteCharacterDataTransformationObject
import com.marvel.domain.model.CharacterEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class DatabaseMapperTest {

    private val databaseMapper = DatabaseMapper()

    @Test
    fun assertMapperConversion() {

        val expectedEntityList = listOf(
            CharacterEntity(1, "Batman", "PHOTO_URL", isFavorite = false)
        )
        val dataObjectList = listOf(
            FavoriteCharacterDataTransformationObject(1, "Batman", "PHOTO_URL")
        )

        val temp = databaseMapper.toEntityList(dataObjectList)

        assertEquals(expectedEntityList[0].id, temp[0].id)
        assertEquals(expectedEntityList[0].name, temp[0].name)
        assertEquals(expectedEntityList[0].imageUrl, temp[0].imageUrl)

        val result = databaseMapper.toDataObjectList(temp)

        assertEquals(expectedEntityList[0].id, result[0].id)
        assertEquals(expectedEntityList[0].name, result[0].name)
        assertEquals(expectedEntityList[0].imageUrl, result[0].photoUrl)
    }

}