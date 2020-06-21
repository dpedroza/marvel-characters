package com.marvel.presentation.ui.main.characters

import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParams
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.usecase.UseCase
import com.marvel.presentation.mapper.ViewObjectMapper
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
        abstract fun mapper(): ViewObjectMapper
        abstract fun isLoading(): Boolean
        abstract fun getCharacters(): UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResultEntity>
        abstract fun updateFavorite(): UseCase.FromCompletable.WithInput<CharacterEntity>
        abstract fun resetPagination()
        abstract fun loadCharacters(query: String? = null)
        abstract fun updateFavorite(characterViewObject: CharacterViewObject)
    }
}