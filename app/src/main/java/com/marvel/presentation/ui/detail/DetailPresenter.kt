package com.marvel.presentation.ui.detail

import com.marvel.R
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.CharacterViewObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val updateFavorite: UpdateFavorite,
    private val mapper: ViewObjectMapper
): DetailContract.Presenter() {

    override fun updateFavorite(characterViewObject: CharacterViewObject) {
        val isFavorite = characterViewObject.isFavorite.not()
        characterViewObject.isFavorite = isFavorite
        val entity = mapper.toEntity(characterViewObject)
        val favoriteMessage = R.string.favorite_added
        val disfavorMessage = R.string.favorite_removed
        val messageId = if (isFavorite) {
            favoriteMessage
        } else {
            disfavorMessage
        }

        updateFavorite.execute(entity)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view?.showMessage(messageId) }
            .also { addDisposable(it) }
    }
}