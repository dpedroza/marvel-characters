package com.marvel.data.remote

import com.marvel.data.local.database.FavoriteDataAccessObject
import com.marvel.data.remote.mapper.ResponseMapper
import com.marvel.data.remote.mapper.ResponseMapperImpl
import com.marvel.data.remote.service.MarvelApiService
import com.marvel.domain.repository.CharactersRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryImplTest {

    @Mock
    lateinit var dao: FavoriteDataAccessObject

    @Mock
    lateinit var service: MarvelApiService

    @Mock
    lateinit var mapper: ResponseMapper

    private lateinit var repository: CharactersRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = CharacterRepositoryImpl(dao, service, mapper)
    }

    @Test
    fun assertGetCharactersSuccessBehavior() {

        val result = repository.getCharacters(0, null).blockingGet()
        assertEquals(result, null)
    }
}