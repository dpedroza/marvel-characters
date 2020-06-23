package com.marvel.presentation.ui.main.characters

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marvel.R
import com.marvel.data.characters.error.NetworkError
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParams
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.usecase.UseCase
import com.marvel.presentation.ui.main.rx.SchedulerProvider
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
    lateinit var getCharacters: UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResultEntity>

    @Mock
    lateinit var updateFavorite: UseCase.FromCompletable.WithInput<CharacterEntity>

    private lateinit var stubPresenter: CharactersContract.Presenter

    private lateinit var trampolineSchedulerProvider: SchedulerProvider

    private val theme = R.style.Theme_Design_Light

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        trampolineSchedulerProvider = TrampolineSchedulerProvider()
        stubPresenter =
            CharacterPresenter(updateFavorite, getCharacters, trampolineSchedulerProvider)
    }

    @Test
    fun testSuccessCharactersBehavior() {

        whenever(getCharacters.execute(anyOrNull())).thenReturn(Single.just(stub))

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {
            onFragment {
                it.presenter = stubPresenter
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
            onView(withText("Nenhum personagem encontrado")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testUnknownErrorBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {

            onFragment {
                it.presenter.onError(RuntimeException("deu ruim"))
            }

            onView(withId(R.id.errorImageView)).check(matches(isDisplayed()))
            onView(withText("Ocorreu um erro inesperado")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testNoInternetBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {

            onFragment {
                it.presenter.onError(NetworkError.NotConnected())
            }

            onView(withId(R.id.errorImageView)).check(matches(isDisplayed()))
            onView(withText("Sem acesso a rede, verifique sua conexão")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun testSlowConnectionBehavior() {

        with(launchFragmentInContainer<CharacterFragment>(themeResId = theme)) {

            onFragment {
                it.presenter.onError(NetworkError.SlowConnection())
            }

            onView(withId(R.id.errorImageView)).check(matches(isDisplayed()))
            onView(withText("Lentidão na rede detectada")).check(matches(isDisplayed()))
        }
    }

    private companion object {
        val stub = GetCharactersResultEntity(
            200,
            "ok",
            200,
            40,
            20,
            listOf(
                CharacterEntity(56, "Doutor Estranho", "banner_url", false),
                CharacterEntity(465, "Homem Aranha", "banner_url", false),
                CharacterEntity(132, "Wonderboy", "banner_url", false),
                CharacterEntity(9789, "Jaspion", "banner_url", false)
            )
        )
    }
}