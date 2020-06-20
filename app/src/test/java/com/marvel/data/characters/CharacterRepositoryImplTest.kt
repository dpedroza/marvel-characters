package com.marvel.data.characters

import com.marvel.data.characters.mapper.ResponseMapper
import com.marvel.data.characters.repo.CharacterRepositoryImpl
import com.marvel.data.characters.service.MarvelApiService
import com.marvel.data.database.FavoriteDao
import com.marvel.data.model.Data
import com.marvel.data.model.MarvelServiceApiResponse
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryImplTest {

    @Mock
    lateinit var dao: FavoriteDao

    @Mock
    lateinit var service: MarvelApiService

    @Spy
    lateinit var mapper: ResponseMapper

    private lateinit var repository: CharactersRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = CharacterRepositoryImpl(
            dao,
            service,
            mapper
        )
    }

    @Test
    fun assertGetCharactersSuccessBehavior() {

        mockSuccessBehavior()

        val result = repository.getCharacters(0, null).blockingGet()

        verify(service).getCharacters(anyString(), anyString(), anyString(), anyInt(), anyOrNull())
        verify(dao).getFavoritesIds()
        verify(mapper).toEntityList(listOf(1, 2, 3), stubApiResponse)

        assertEquals(200, result.code)
        assertEquals("ok", result.status)
        assertEquals(20, result.paginationOffset)
        assertEquals(true, result.characters.isEmpty())
    }

    private fun mockSuccessBehavior() {

        val getCharacters =
            service.getCharacters(anyString(), anyString(), anyString(), anyInt(), anyOrNull())
        `when`(getCharacters).thenReturn(Single.just(stubApiResponse))

        val getFavoriteIds = dao.getFavoritesIds()
        `when`(getFavoriteIds).thenReturn(listOf(1, 2, 3))

        val dataTransformation = mapper.toEntityList(any(), any())
        `when`(dataTransformation).thenReturn(stubResultEntity)
    }

    private companion object {

        val stubApiResponse = MarvelServiceApiResponse(
            "attributionHTML",
            "attribution",
            123,
            "copyleft",
            Data(
                123,
                456,
                789,
                emptyList(),
                20
            )
            , "etag", "ok"
        )

        val stubResultEntity = GetCharactersResultEntity(
            200,
            "ok",
            200,
            40,
            20,
            emptyList()
        )
    }
}