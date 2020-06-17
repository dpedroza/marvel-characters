package com.marvel.presentation.ui.characters

import androidx.paging.PagedList
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter

interface CharactersContract {

    interface View : BaseView {
        fun showCharacters(characters: List<CharacterViewObject>)
    }

    abstract class Presenter : CorePresenter<View>() {
        abstract fun loadCharacters()
    }
}