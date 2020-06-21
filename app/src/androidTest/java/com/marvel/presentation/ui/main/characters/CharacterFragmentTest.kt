package com.marvel.presentation.ui.main.characters

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marvel.R
import com.marvel.domain.model.GetCharactersParams
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.usecase.UseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
class CharacterFragmentTest {

    @Mock
    lateinit var presenter: CharactersContract.Presenter

    @Mock
    lateinit var getCharacters: UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResultEntity>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testEventFragment() {

        Mockito.`when`(presenter.).thenReturn(emptyList<>())

        with(launchFragmentInContainer<CharacterFragment>(themeResId = R.style.Theme_Design)) {
            onFragment { fragment ->
                fragment.presenter = presenter
            }

        }

    }


}