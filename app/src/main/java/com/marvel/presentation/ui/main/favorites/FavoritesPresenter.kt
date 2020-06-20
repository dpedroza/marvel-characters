package com.marvel.presentation.ui.main.favorites

import com.marvel.R
import com.marvel.domain.usecase.GetFavorites
import com.marvel.presentation.mapper.ViewObjectMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesPresenter @Inject constructor(
    private val getFavorites: GetFavorites,
    private val mapper: ViewObjectMapper
) : FavoritesContract.Presenter() {

    override fun loadFavorites() {

        getFavorites.execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    val characters = mapper.toViewObjectList(result)
                    if (characters.isNotEmpty()) {
                        view?.showFavorites(characters)
                    } else {
                        view?.showEmptyState()
                    }
                },
                {
                    view?.showMessage(R.string.message_unknown_error)
                }
            )
            .also { addDisposable(it) }
    }
}

