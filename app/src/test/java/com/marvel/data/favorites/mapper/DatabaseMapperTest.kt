package com.marvel.data.favorites.mapper

import com.marvel.data.favorites.model.CharacterDto
import com.marvel.domain.core.CharacterEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class DatabaseMapperTest {

    private val databaseMapper = DatabaseMapper()

    @Test
    fun assertMapperConversion() {

        val expectedEntityList = listOf(
            CharacterEntity(
                id = 1,
                name = "Batman",
                imageUrl = "PHOTO_URL",
                isFavorite = false,
                description = "description"
            )
        )
        val dataObjectList = listOf(
            CharacterDto(
                1,
                "Batman",
                "PHOTO_URL"
            )
        )

        val temp = databaseMapper.toEntityList(dataObjectList)

        assertEquals(expectedEntityList[0].id, temp[0].id)
        assertEquals(expectedEntityList[0].name, temp[0].name)
        assertEquals(expectedEntityList[0].imageUrl, temp[0].imageUrl)

        val result = databaseMapper.toDtoList(temp)

        assertEquals(expectedEntityList[0].id, result[0].id)
        assertEquals(expectedEntityList[0].name, result[0].name)
        assertEquals(expectedEntityList[0].imageUrl, result[0].photoUrl)
    }
}
