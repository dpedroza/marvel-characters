package com.marvel.presentation.detail

import com.marvel.domain.characters.entity.ComicsEntity
import com.marvel.domain.characters.entity.SeriesEntity
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.core.BaseView
import com.marvel.presentation.core.CorePresenter

interface DetailContract {

    interface View : BaseView {

        fun showName()
        fun showDescription()
        fun showSeries(series: List<SeriesEntity>)
        fun showComics(comics: List<ComicsEntity>)
        fun hideComics()
        fun hideSeries()
    }

    abstract class Presenter : CorePresenter<View>() {

        abstract fun updateFavorite(characterViewObject: CharacterViewObject)
        abstract fun loadSeries(characterViewObject: CharacterViewObject)
        abstract fun loadComics(characterViewObject: CharacterViewObject)
    }
}
