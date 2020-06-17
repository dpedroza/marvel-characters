package com.marvel.presentation.ui.characters

import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter
import com.marvel.presentation.model.CharacterViewObject

interface CharactersContract {

    interface View: BaseView {
        fun showCharacters(characters: List<CharacterViewObject>)
    }

    abstract class Presenter: CorePresenter<View>() {
        abstract fun loadCharacters()
    }
}