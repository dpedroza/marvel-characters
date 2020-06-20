package com.marvel.presentation.ui.main.characters

import com.marvel.R
import com.marvel.data.remote.error.NetworkError
import com.marvel.data.remote.error.composeErrorTransformers
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParameters
import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.model.PaginationData
import com.marvel.presentation.ui.main.rx.applyDefaultSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenter @Inject constructor(
    private val updateFavorite: UpdateFavorite,
    private val getCharacters: GetCharacters,
    private var pagination: PaginationData,
    private val mapper: ViewObjectMapper
) : CharactersContract.Presenter() {

    var isLoading = false

    override fun loadCharacters(
        query: String?,
        resetAdapter: Boolean
    ) {
        if (pagination.isFinalPage) return
        if (resetAdapter) pagination.reset()
        val parameters = GetCharactersParameters(pagination.offset)
        parameters.query = query

        view?.showLoading()
        isLoading = true

        getCharacters.execute(parameters)
            .applyDefaultSchedulers()
            .composeErrorTransformers()
            .subscribe(
                { result ->
                    onUpdateCharacters(
                        result.characters,
                        resetAdapter
                    )
                    onUpdatePagination(
                        result.currentCount,
                        result.totalCount,
                        result.paginationOffset
                    )
                },
                {
                    view?.hideLoading()
                    view?.showMessage(getErrorMessage(it))
                    isLoading = false
                }
            )
            .also { addDisposable(it) }
    }

    override fun updateFavorite(characterViewObject: CharacterViewObject) {

        val isFavorite = characterViewObject.isFavorite.not()
        characterViewObject.isFavorite = isFavorite
        val entity = mapper.toEntity(characterViewObject)

        updateFavorite.execute(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onUpdateFavorite(isFavorite) }
            .also { addDisposable(it) }
    }

    private fun onUpdateCharacters(characters: List<CharacterEntity>, reset: Boolean) {
        view?.hideLoading()

        if (characters.isNotEmpty()) {
            mapper.toViewObjectList(characters).let {
                view?.showCharacters(it, reset)
            }
        } else {
            view?.showEmptyState()
        }
        isLoading = false
    }

    private fun onUpdateFavorite(isFavorite: Boolean): Action {
        val messageId = if (isFavorite) {
            R.string.favorite_added
        } else {
            R.string.favorite_removed
        }
        return Action { view?.showToast(messageId) }
    }

    private fun onUpdatePagination(
        currentCount: Int,
        totalCount: Int,
        offset: Int
    ) {
        pagination.isFinalPage = currentCount == totalCount
        pagination.offset = offset + currentCount
    }

    private fun getErrorMessage(error: Throwable): Int {
        return when (error) {
            is NetworkError.NotConnected -> R.string.message_no_internet
            is NetworkError.SlowConnection -> R.string.message_slow_internet
            is NetworkError.Canceled -> R.string.message_unknown_error
            else -> R.string.message_unknown_error
        }
    }
}

