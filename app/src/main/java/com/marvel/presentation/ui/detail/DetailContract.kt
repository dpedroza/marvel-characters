package com.marvel.presentation.ui.detail

import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter

interface DetailContract {

        interface View : BaseView

        abstract class Presenter : CorePresenter<View>() {

            abstract fun updateFavorite(characterViewObject: CharacterViewObject)
        }
}