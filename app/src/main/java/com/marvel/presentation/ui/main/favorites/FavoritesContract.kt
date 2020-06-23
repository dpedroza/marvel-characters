package com.marvel.presentation.ui.main.favorites

import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter

interface FavoritesContract {

    interface View : BaseView {

        fun showFavorites(characters: List<CharacterViewObject>)
        fun showEmptyState()
    }

    abstract class Presenter : CorePresenter<View>() {

        abstract fun loadFavorites()
    }
}
