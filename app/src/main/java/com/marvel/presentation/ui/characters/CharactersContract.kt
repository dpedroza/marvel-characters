package com.marvel.presentation.ui.characters

import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter

interface CharactersContract {

    interface View : BaseView {

        fun showCharacters(
            characters: List<CharacterViewObject>,
            clear: Boolean = false
        )
    }

    abstract class Presenter : CorePresenter<View>() {

        abstract fun loadCharacters(
            query: String = "",
            clear: Boolean = false
        )
    }
}