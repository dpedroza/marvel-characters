package com.marvel.presentation.main.favorites

import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.core.BaseView
import com.marvel.presentation.core.CorePresenter

interface FavoritesContract {

    interface View : BaseView {

        fun showFavorites(characters: List<CharacterViewObject>)
        fun showEmptyState()
    }

    abstract class Presenter : CorePresenter<View>() {

        abstract fun loadFavorites()
    }
}
