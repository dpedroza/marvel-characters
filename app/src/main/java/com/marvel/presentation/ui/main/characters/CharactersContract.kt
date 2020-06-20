package com.marvel.presentation.ui.main.characters

import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter

interface CharactersContract {

    interface View : BaseView {

        fun showCharacters(characters: List<CharacterViewObject>)
        fun showToast(messageId: Int, name: String)
        fun showEmptyState()
        fun showLoading()
        fun hideLoading()
    }

    abstract class Presenter : CorePresenter<View>() {
        abstract fun isLoading() : Boolean
        abstract fun resetPagination()
        abstract fun loadCharacters(query: String? = null)
        abstract fun updateFavorite(characterViewObject: CharacterViewObject)
    }
}