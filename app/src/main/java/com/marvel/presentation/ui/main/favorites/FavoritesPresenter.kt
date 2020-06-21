package com.marvel.presentation.ui.main.favorites

import com.marvel.R
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.usecase.UseCase
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.ui.main.rx.schedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FavoritesPresenter @Inject constructor(
    private val getFavorites: UseCase.FromSingle.WithoutInput<List<CharacterEntity>>,
    private val mapper: ViewObjectMapper
) : FavoritesContract.Presenter() {

    override fun loadFavorites() {

        getFavorites.execute()
            .schedulers()
            .subscribeBy(
                onSuccess = { characters ->
                    onFavoriteUpdate(characters)
                },
                onError = {
                    view?.showMessage(R.string.message_unknown_error)
                }
            )
            .also { addDisposable(it) }
    }

    private fun onFavoriteUpdate(characters: List<CharacterEntity>) {
        val charactersViewObject = mapper.toViewObjectList(characters)
        if (charactersViewObject.isNotEmpty()) {
            view?.showFavorites(charactersViewObject)
        } else {
            view?.showEmptyState()
        }
    }
}

