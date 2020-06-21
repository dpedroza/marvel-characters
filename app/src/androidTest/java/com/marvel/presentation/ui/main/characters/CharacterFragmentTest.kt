package com.marvel.presentation.ui.main.characters

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marvel.R
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParams
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.usecase.UseCase
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class CharacterFragmentTest {

    @Mock
    lateinit var mockGetCharacters: UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResultEntity>

    @Mock
    lateinit var mockUpdateFavorite: UseCase.FromCompletable.WithInput<CharacterEntity>

    @Mock
    lateinit var mockPresenter: CharactersContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testEventFragment() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = R.style.Theme_Design)) {

            whenever(mockGetCharacters.execute(anyOrNull())).thenReturn(Single.just(stub))
            whenever(mockPresenter.getCharacters()).thenReturn(mockGetCharacters)
            whenever(mockPresenter.updateFavorite()).thenReturn(mockUpdateFavorite)

            onFragment { it.presenter = mockPresenter }
            onView(withId(R.id.emptyText)).check(matches(isDisplayed()))
        }
    }

    private companion object {
        val stub = GetCharactersResultEntity(
            200,
            "ok",
            200,
            40,
            20,
            emptyList()
        )
    }
}