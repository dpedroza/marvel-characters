package com.marvel.data.remote

import com.marvel.data.local.database.FavoriteDataAccessObject
import com.marvel.data.remote.mapper.ResponseMapper
import com.marvel.data.remote.service.MarvelApiService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class CharacterRepositoryTest {

    val dao = mock(FavoriteDataAccessObject::class.java)
    val service = mock(MarvelApiService::class.java)
    val mapper = mock(ResponseMapper::class.java)

    private val repository = CharacterRepositoryImpl(dao, service, mapper)

    @Test
    fun assertGetCharactersSuccessBehavior() {

        val result = repository.getCharacters(0, null).blockingGet()
        assertEquals(result, null)
    }
}