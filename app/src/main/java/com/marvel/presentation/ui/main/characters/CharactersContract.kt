package com.marvel.presentation.ui.main.characters

import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter
import io.reactivex.functions.Action

interface CharactersContract {

    interface View : BaseView {

        fun showCharacters(
            characters: List<CharacterViewObject>,
            clear: Boolean = false
        )
        fun showEmptyState()
        fun showLoading()
        fun hideLoading()
        fun showToast(messageId: Int, name: String)
    }

    abstract class Presenter : CorePresenter<View>() {

        abstract fun loadCharacters(
            query: String? = null,
            resetAdapter: Boolean = false
        )

        abstract fun updateFavorite(
            characterViewObject: CharacterViewObject
        )
    }
}