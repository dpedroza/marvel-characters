package com.marvel.presentation.detail

import com.marvel.R
import com.marvel.domain.characters.params.GetComicsParams
import com.marvel.domain.characters.params.GetSeriesParams
import com.marvel.domain.characters.usecase.GetComics
import com.marvel.domain.characters.usecase.GetSeries
import com.marvel.domain.favorites.usecase.UpdateFavorite
import com.marvel.presentation.schedulers.SchedulerProvider
import com.marvel.presentation.schedulers.ioComputationSchedulers
import com.marvel.presentation.schedulers.ioUiSchedulers
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.CharacterViewObject
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val updateFavorite: UpdateFavorite,
    private val getComics: GetComics,
    private val getSeries: GetSeries,
    private val schedulerProvider: SchedulerProvider
) : DetailContract.Presenter() {

    private val mapper = ViewObjectMapper()

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
            .ioComputationSchedulers(schedulerProvider)
            .subscribeBy { view?.showMessage(messageId) }
            .also { addDisposable(it) }
    }

    override fun loadSeries(characterViewObject: CharacterViewObject) {
        val params = GetSeriesParams(
            offset = 0,
            characterId = characterViewObject.id
        )

        getSeries.execute(params)
            .ioUiSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = {
                    view?.showSeries(it.series)
                },
                onError = {
                    view?.showMessage(R.string.message_unknown_error)
                }
            )
            .also { addDisposable(it) }
    }

    override fun loadComics(characterViewObject: CharacterViewObject) {
        val params = GetComicsParams(
            offset = 0,
            characterId = characterViewObject.id
        )

        getComics.execute(params)
            .ioUiSchedulers(schedulerProvider)
            .subscribeBy(
                onSuccess = {
                    view?.showComics(it.comics)
                },
                onError = {
                    view?.showMessage(R.string.message_unknown_error)
                }
            )
            .also { addDisposable(it) }
    }
}
