package com.marvel.presentation.ui.detail

import com.marvel.domain.model.entity.ComicsEntity
import com.marvel.domain.model.entity.SeriesEntity
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.core.BaseView
import com.marvel.presentation.ui.core.CorePresenter

interface DetailContract {

    interface View : BaseView {

        fun showSeries(series: List<SeriesEntity>)
        fun showComics(series: List<ComicsEntity>)
    }

    abstract class Presenter : CorePresenter<View>() {

        abstract fun updateFavorite(characterViewObject: CharacterViewObject)
        abstract fun loadSeries(characterViewObject: CharacterViewObject)
        abstract fun loadComics(characterViewObject: CharacterViewObject)
    }
}
