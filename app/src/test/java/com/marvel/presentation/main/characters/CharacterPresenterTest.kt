package com.marvel.presentation.main.characters

import com.marvel.domain.core.CharacterEntity
import com.marvel.domain.characters.params.GetCharactersParams
import com.marvel.domain.characters.model.GetCharactersResult
import com.marvel.domain.core.UseCase
import com.marvel.presentation.schedulers.SchedulerProvider
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharacterPresenterTest {

    @Mock
    lateinit var mockGetCharacters: UseCase.FromSingle.WithInput
    <GetCharactersParams, GetCharactersResult>

    @Mock
    lateinit var mockUpdateFavorite: UseCase.FromCompletable.WithInput<CharacterEntity>

    private lateinit var presenter: CharacterPresenter

    private lateinit var trampolineScheduler: SchedulerProvider

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        trampolineScheduler = TestSchedulerProvider()
        presenter = CharacterPresenter(mockUpdateFavorite, mockGetCharacters, trampolineScheduler)
    }

    @Test
    fun emptyQueryBecomesNullGetParameterBehavior() {
        val query = ""

        val result = presenter.getParameters(query)

        assertNull(result.query)
    }
}
