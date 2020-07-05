package com.marvel.presentation.ui.main.characters

import com.marvel.domain.model.entity.CharacterEntity
import com.marvel.domain.model.params.GetCharactersParams
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
        abstract fun isLoading(): Boolean
        abstract fun loadCharacters(query: String? = null)
        abstract fun getParameters(query: String?): GetCharactersParams

        abstract fun resetPagination()
        abstract fun onUpdatePagination(currentCount: Int, offset: Int)
        abstract fun onUpdateCharacters(characters: List<CharacterEntity>)
        abstract fun onUpdateFavorite(characterViewObject: CharacterViewObject)
        abstract fun onError(it: Throwable)
    }
}
