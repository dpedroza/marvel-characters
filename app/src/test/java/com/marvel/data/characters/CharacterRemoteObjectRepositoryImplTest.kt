package com.marvel.data.characters

import com.marvel.data.characters.mapper.ResponseMapper
import com.marvel.data.characters.mapper.ResponseMapperImpl
import com.marvel.data.characters.repo.CharacterRepositoryImpl
import com.marvel.data.characters.service.MarvelApiService
import com.marvel.data.database.FavoriteDao
import com.marvel.data.model.*
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Assert.*
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
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class CharacterRemoteObjectRepositoryImplTest {

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
    }

    @Test
    fun assertGetCharactersSuccessBehavior() {

        mockSuccessResponseBehavior()

        repository = CharacterRepositoryImpl(
            dao,
            service,
            mapper
        )

        val result = repository.getCharacters(0, null).blockingGet()

        verify(service).getCharacters(anyString(), anyString(), anyString(), anyInt(), anyOrNull())
        verify(dao).getFavoritesIds()
        verify(mapper).toEntityList(listOf(1, 2, 3), stubApiResponse)

        assertEquals(200, result.code)
        assertEquals("ok", result.status)
        assertEquals(20, result.paginationOffset)
        assertEquals(true, result.characters.isEmpty())
    }



    @Test(expected = RuntimeException::class)
    fun assertGetCharactersErrorBehavior() {

        mockErrorBehavior()

        repository = CharacterRepositoryImpl(
            dao,
            service,
            mapper
        )

        repository.getCharacters(0, null).blockingGet()
    }

    @Test
    fun assertLocalFavoriteInfoAndRemoteCharactersFavoriteAttributionBehavior() {

        mockSuccessResponseBehavior()

        repository = CharacterRepositoryImpl(
            dao,
            service,
            ResponseMapperImpl()
        )

        val result = repository.getCharacters(0, null).blockingGet()

        assertTrue(result.characters[0].isFavorite)
        assertFalse(result.characters[1].isFavorite)
    }

    private fun mockSuccessResponseBehavior() {

        val getCharacters =
            service.getCharacters(anyString(), anyString(), anyString(), anyInt(), anyOrNull())
        `when`(getCharacters).thenReturn(Single.just(stubApiResponse))

        val getFavoriteIds = dao.getFavoritesIds()
        val favorites = listOf(1, 2, 3)
        `when`(getFavoriteIds).thenReturn(favorites)

        val dataTransformation = mapper.toEntityList(any(), any())
        `when`(dataTransformation).thenReturn(stubResultEntity)
    }

    private fun mockErrorBehavior() {

        val getCharacters =
            service.getCharacters(anyString(), anyString(), anyString(), anyInt(), anyOrNull())
        `when`(getCharacters).thenReturn(Single.error(Throwable("algo terrível aconteceu")))

        val getFavoriteIds = dao.getFavoritesIds()
        `when`(getFavoriteIds).thenReturn(emptyList())
    }

    private companion object {

        val stubCharacterLocalFavorite = CharacterRemoteObject(
            Comics(
                0,
                "",
                emptyList(),
                3
            ),
            "description",
            Events(
                0,
                "",
                emptyList(),
                5
            ),
            3,
            "modified",
            "name",
            "resourceURI",
            Series(
                789,
                "uri",
                emptyList(),
                6
            ),
            Stories(
                89,
                "uri",
                emptyList(),
                456
            ),
            Thumbnail(
                "jpg",
                "path"
            ),
            emptyList()
        )

        val stubCharacterLocalNotFavorite = CharacterRemoteObject(
            Comics(
                0,
                "",
                emptyList(),
                3
            ),
            "description",
            Events(
                0,
                "",
                emptyList(),
                5
            ),
            5,
            "modified",
            "name",
            "resourceURI",
            Series(
                789,
                "uri",
                emptyList(),
                6
            ),
            Stories(
                89,
                "uri",
                emptyList(),
                456
            ),
            Thumbnail(
                "jpg",
                "path"
            ),
            emptyList()
        )

        val stubApiResponse = MarvelServiceApiResponse(
            "attributionHTML",
            "attribution",
            123,
            "copyleft",
            Data(
                123,
                456,
                789,
                listOf(
                    stubCharacterLocalFavorite,
                    stubCharacterLocalNotFavorite
                ),
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