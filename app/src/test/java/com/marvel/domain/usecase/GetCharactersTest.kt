package com.marvel.domain.usecase

import com.marvel.domain.model.params.GetCharactersParams
import com.marvel.domain.model.result.GetCharactersResult
import com.marvel.domain.repository.CharactersRepository
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCharactersTest {

    @Mock
    lateinit var repository: CharactersRepository

    lateinit var getCharacters: GetCharacters

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCharacters = GetCharacters(repository)
    }

    @Test
    fun assertSuccessBehavior() {

        whenever(
            repository.getCharacters(
                anyOrNull(),
                anyString()
            )
        ).thenReturn(
            Single.just(
                stubResultEntity
            )
        )

        val params = GetCharactersParams()
        params.query = "query"
        params.offset = 0

        val result = getCharacters.execute(params).blockingGet()

        val expectedCode = 200
        val expectedStatus = "ok"
        val expectedOffset = 20

        assertEquals(expectedCode, result.code)
        assertEquals(expectedStatus, result.status)
        assertEquals(expectedOffset, result.paginationOffset)
        assertTrue(result.characters.isEmpty())
    }

    @Test(expected = RuntimeException::class)
    fun assertErrorBehavior() {

        whenever(
            repository.getCharacters(
                anyOrNull(),
                anyString()
            )
        ).thenReturn(
            Single.error(
                Throwable("sou um erro")
            )
        )

        val params = GetCharactersParams()
        params.query = "query"
        params.offset = 0

        getCharacters.execute(params).blockingGet()
    }

    private companion object {

        const val stubOkCode = 200
        const val stubCurrentCount = 40
        const val stubCount = 6000
        const val stubOffset = 20
        const val stubOkStatus = "ok"

        val stubResultEntity =
            GetCharactersResult(
                stubOkCode,
                stubOkStatus,
                stubCount,
                stubCurrentCount,
                stubOffset,
                emptyList()
            )
    }
}
