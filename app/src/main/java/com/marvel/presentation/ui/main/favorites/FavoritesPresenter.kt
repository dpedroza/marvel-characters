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

        view?.showLoading()

        getFavorites.execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    view?.hideLoading()
                    val characters = mapper.toViewObjectList(result)
                    if (characters.isNotEmpty()) {
                        view?.showFavorites(characters)
                    } else {
                        view?.showEmptyState()
                    }
                },
                {
                    view?.hideLoading()
                    view?.showMessage(R.string.heroes)
                }
            )
            .also { addDisposable(it) }
    }
}

