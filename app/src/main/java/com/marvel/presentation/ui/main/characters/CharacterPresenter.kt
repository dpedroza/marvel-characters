package com.marvel.presentation.ui.main.characters

import com.marvel.R
import com.marvel.data.remote.NetworkError
import com.marvel.data.remote.composeErrorTransformers
import com.marvel.domain.model.GetCharactersParameters
import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.model.PaginationData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenter @Inject constructor(
    private val updateFavorite: UpdateFavorite,
    private val getCharacters: GetCharacters,
    private val mapper: ViewObjectMapper,
    private var pagination: PaginationData
) : CharactersContract.Presenter() {

    override fun loadCharacters(
        query: String,
        reset: Boolean
    ) {
        if (pagination.isFinalPage) return
        if (reset) pagination.reset()

        view?.showLoading()

        val parameters = GetCharactersParameters(pagination.offset, query)

        getCharacters.execute(parameters)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .composeErrorTransformers()
            .subscribe(

                { result ->
                    view?.hideLoading()

                    val characters = mapper.toViewObjectList(result.characters)
                    if (characters.isNotEmpty()) {
                        view?.showCharacters(characters, reset)
                    } else {
                        view?.showEmptyState()
                    }

                    updatePagination(
                        result.currentCount,
                        result.totalCount,
                        result.paginationOffset
                    )
                },
                {
                    view?.hideLoading()
                    view?.showMessage(getErrorMessage(it))
                }
            )
            .also { addDisposable(it) }

    }

    private fun getErrorMessage(error: Throwable): Int {
        return when (error) {
            is NetworkError.NotConnected -> R.string.message_no_internet
            is NetworkError.SlowConnection -> R.string.message_slow_internet
            is NetworkError.Canceled -> R.string.message_unknown_error
            else -> R.string.message_unknown_error
        }
    }

    override fun updateFavorite(characterViewObject: CharacterViewObject) {
        val entity = mapper.toEntity(characterViewObject)
        updateFavorite.execute(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .also { addDisposable(it) }
    }

    private fun updatePagination(currentCount: Int, totalCount: Int, offset: Int) {
        pagination.isFinalPage = currentCount == totalCount
        pagination.offset = offset + currentCount
    }
}

