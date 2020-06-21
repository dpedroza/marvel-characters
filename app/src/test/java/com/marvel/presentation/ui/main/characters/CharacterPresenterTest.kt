package com.marvel.presentation.ui.main.characters

import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParams
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.usecase.UseCase
import com.marvel.presentation.mapper.ViewObjectMapperImpl
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharacterPresenterTest {

    @Mock
    lateinit var mockGetCharacters: UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResultEntity>

    @Mock
    lateinit var mockUpdateFavorite: UseCase.FromCompletable.WithInput<CharacterEntity>

    lateinit var presenter: CharacterPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CharacterPresenter(
            mockUpdateFavorite,
            mockGetCharacters,
            ViewObjectMapperImpl()
        )
    }

    @Test
    fun emptyQueryBecomesNullGetParameterBehavior() {
        val query = ""

        val result = presenter.getParameters(query)

        assertNull(result.query)
    }
}