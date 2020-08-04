package com.marvel.data.characters

import com.marvel.data.characters.model.characters.CharacterRemoteObject
import com.marvel.data.characters.model.characters.GetCharactersApiResponse
import com.marvel.data.characters.model.common.Comics
import com.marvel.data.characters.model.common.Data
import com.marvel.data.characters.model.common.Events
import com.marvel.data.characters.model.common.Series
import com.marvel.data.characters.model.common.Stories
import com.marvel.data.characters.model.common.Thumbnail
import com.marvel.data.characters.repository.CharacterRepositoryImpl
import com.marvel.data.characters.service.ApiService
import com.marvel.data.favorites.database.FavoriteDao
import com.marvel.domain.characters.CharactersRepository
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @Mock
    lateinit var dao: FavoriteDao

    @Mock
    lateinit var service: ApiService

    private lateinit var repository: CharactersRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = CharacterRepositoryImpl(dao, service)
    }

    @Test
    fun assertGetCharactersSuccessBehavior() {

        val getCharacters = service.getCharacters(anyOrNull(), anyInt())
        whenever(getCharacters).thenReturn(Single.just(stubApiResponse))

        val getFavoriteIds = dao.getFavoritesIds()

        whenever(getFavoriteIds).thenReturn(stubFavorites)

        val result = repository.getCharacters(null, 0).blockingGet()

        verify(service).getCharacters(anyOrNull(), anyInt())
        verify(dao).getFavoritesIds()

        val expectedCode = 200
        val expectedStatus = "ok"
        val expectedOffset = 20
        val expectedSize = 2

        assertEquals(expectedCode, result.code)
        assertEquals(expectedStatus, result.status)
        assertEquals(expectedOffset, result.paginationOffset)
        assertEquals(expectedSize, result.characters.size)
    }

    @Test(expected = RuntimeException::class)
    fun assertGetCharactersErrorBehavior() {

        val getCharacters = service.getCharacters(anyOrNull(), anyInt())
        whenever(getCharacters).thenReturn(Single.error(Throwable("algo terrível aconteceu")))

        val getFavoriteIds = dao.getFavoritesIds()
        whenever(getFavoriteIds).thenReturn(emptyList())

        repository.getCharacters(null, 0).blockingGet()
    }

    @Test
    fun assertLocalFavoriteInfoAndRemoteCharactersFavoriteAttributionBehavior() {

        val getCharacters =
            service.getCharacters(anyOrNull(), anyInt())
        whenever(getCharacters).thenReturn(Single.just(stubApiResponse))

        val getFavoriteIds = dao.getFavoritesIds()

        whenever(getFavoriteIds).thenReturn(stubFavorites)

        val result = repository.getCharacters(null, 0).blockingGet()

        assertTrue(result.characters[0].isFavorite)
        assertFalse(result.characters[1].isFavorite)
    }

    private companion object {

        val stubFavorites = listOf(1, 2, 3)

        const val stubFavoriteId = 2
        const val stubNotFavoriteId = 5

        const val favoriteName = "o favorito"
        const val notFavoriteName = "não gosto muito"
        const val stubAvailable = 6
        const val stubUri = "stubUri"
        const val stubReturned = 23089
        const val stubExtension = "jpg"
        const val stubPath = "path"
        const val stubDescription = "um personagem muito importante na trama"
        const val stubModified = "modified"
        const val stubResourceUri = "resource_uri"
        const val stubCount = 6000
        const val stubAttributionHTML = "attributionHTML"
        const val stubAttributionText = "attribution text"
        const val stubCopyright = "copyleft"
        const val stubOffset = 20
        const val stubOkCode = 200
        const val stubOkStatus = "ok"
        const val stubEtag = "etag"

        val stubComics = Comics(
            stubAvailable,
            stubUri,
            emptyList(),
            stubReturned
        )
        val stubEvents = Events(
            stubAvailable,
            stubUri,
            emptyList(),
            stubReturned
        )
        val stubSeries = Series(
            stubAvailable,
            stubUri,
            emptyList(),
            stubReturned
        )
        val stubStories = Stories(
            stubAvailable,
            stubUri,
            emptyList(),
            stubReturned
        )
        val stubThumbnail =
            Thumbnail(stubExtension, stubPath)

        val stubCharacterLocalFavorite =
            CharacterRemoteObject(
                stubComics,
                stubDescription,
                stubEvents,
                stubFavoriteId,
                stubModified,
                favoriteName,
                stubResourceUri,
                stubSeries,
                stubStories,
                stubThumbnail,
                emptyList()
            )

        val stubCharacterLocalNotFavorite =
            CharacterRemoteObject(
                stubComics,
                stubDescription,
                stubEvents,
                stubNotFavoriteId,
                stubModified,
                notFavoriteName,
                stubResourceUri,
                stubSeries,
                stubStories,
                stubThumbnail,
                emptyList()
            )

        val stubData = Data(
            stubCount,
            stubCount,
            stubOffset,
            listOf(
                stubCharacterLocalFavorite,
                stubCharacterLocalNotFavorite
            ),
            stubCount
        )

        val stubApiResponse =
            GetCharactersApiResponse(
                stubAttributionHTML,
                stubAttributionText,
                stubOkCode,
                stubCopyright,
                stubData,
                stubEtag,
                stubOkStatus
            )
    }
}
