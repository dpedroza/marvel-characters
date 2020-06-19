package com.marvel.presentation.ui.main.characters

import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter

interface CharactersContract {

    interface View : BaseView {

        fun showCharacters(
            characters: List<CharacterViewObject>,
            clear: Boolean = false
        )

        fun showEmptyState()
    }

    abstract class Presenter : CorePresenter<View>() {

        abstract fun loadCharacters(
            query: String = "",
            reset: Boolean = false
        )

        abstract fun updateFavorite(
            characterViewObject: CharacterViewObject
        )
    }
}