package com.marvel.presentation.main.favorites

import com.marvel.R
import com.marvel.domain.characters.model.entity.CharacterEntity
import com.marvel.domain.UseCase
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.schedulers.SchedulerProvider
import com.marvel.presentation.schedulers.ioUiSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FavoritesPresenter @Inject constructor(
    private val getFavorites: UseCase.Single.WithoutInput<List<CharacterEntity>>,
    private val schedulerProvider: SchedulerProvider
) : FavoritesContract.Presenter() {

    private val mapper = ViewObjectMapper()

    override fun loadFavorites() {

        getFavorites.execute()
            .ioUiSchedulers(schedulerProvider)
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
