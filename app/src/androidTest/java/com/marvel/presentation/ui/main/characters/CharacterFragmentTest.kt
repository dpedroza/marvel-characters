package com.marvel.presentation.ui.main.characters

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marvel.R
import com.marvel.data.characters.error.NetworkError
import com.marvel.domain.model.CharacterEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class CharacterFragmentTest {

    private val theme = R.style.Theme_Design_Light

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testSuccessCharactersBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {
            onFragment {
                it.presenter.onUpdateCharacters(
                    listOf(
                        CharacterEntity(56, "Doutor Estranho", "banner_url", false),
                        CharacterEntity(465, "Homem Aranha", "banner_url", false),
                        CharacterEntity(132, "Wonderboy", "banner_url", false),
                        CharacterEntity(9789, "Jaspion", "banner_url", false)
                    )
                )
            }

            onView(withText("Doutor Estranho")).check(matches(isDisplayed()))
            onView(withText("Homem Aranha")).check(matches(isDisplayed()))
            onView(withText("Wonderboy")).check(matches(isDisplayed()))
            onView(withText("Jaspion")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testEmptyCharactersBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {

            onFragment {
                it.presenter.onUpdateCharacters(emptyList())
            }

            onView(withId(R.id.emptyText)).check(matches(isDisplayed()))
            onView(withText("No character found")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testUnknownErrorBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {

            onFragment {
                it.presenter.onError(RuntimeException("deu ruim"))
            }

            onView(withId(R.id.errorImageView)).check(matches(isDisplayed()))
            onView(withText("An unexpected error has occurred")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testNoInternetBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {

            onFragment {
                it.presenter.onError(NetworkError.NotConnected())
            }

            onView(withId(R.id.errorImageView)).check(matches(isDisplayed()))
            onView(withText("No internet, check your connection")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testSlowConnectionBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {

            onFragment {
                it.presenter.onError(NetworkError.SlowConnection())
            }

            onView(withId(R.id.errorImageView)).check(matches(isDisplayed()))
            onView(withText("Slow network detected")).check(matches(isDisplayed()))
        }
    }
}