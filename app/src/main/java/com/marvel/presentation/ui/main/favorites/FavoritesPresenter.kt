package com.marvel.presentation.ui.main.favorites

import com.marvel.R
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.usecase.GetFavorites
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.ui.main.rx.applyDefaultSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesPresenter @Inject constructor(
    private val getFavorites: GetFavorites,
    private val mapper: ViewObjectMapper
) : FavoritesContract.Presenter() {

    override fun loadFavorites() {

        getFavorites.execute()
            .applyDefaultSchedulers()
            .subscribe(
                { result ->
                    onFavoriteUpdate(result)
                },
                {
                    view?.showMessage(R.string.message_unknown_error)
                }
            )
            .also { addDisposable(it) }
    }

    private fun onFavoriteUpdate(characters: List<CharacterEntity>) {
        mapper.toViewObjectList(characters).let {
            if (it.isNotEmpty()) {
                view?.showFavorites(it)
            } else {
                view?.showEmptyState()
            }
        }
    }
}

