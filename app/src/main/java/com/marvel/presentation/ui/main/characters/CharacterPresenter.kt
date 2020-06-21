package com.marvel.presentation.ui.main.characters

import com.marvel.R
import com.marvel.data.characters.error.NetworkError
import com.marvel.data.characters.error.networkErrorTransformers
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParams
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.usecase.UseCase
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.ui.main.rx.schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenter @Inject constructor(
    private val updateFavorite: UseCase.FromCompletable.WithInput<CharacterEntity>,
    private val getCharacters: UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResultEntity>,
    private val mapper: ViewObjectMapper
) : CharactersContract.Presenter() {

    private var loading = false
    private var paginationOffset = 0

    override fun isLoading() = loading
    override fun resetPagination() { paginationOffset = 0 }

    override fun loadCharacters(query: String?) {

        view?.showLoading()

        getCharacters.execute(getParameters(query))
            .doOnSubscribe { loading = true }
            .doAfterTerminate { loading = false }
            .doAfterTerminate { view?.hideLoading() }
            .networkErrorTransformers()
            .schedulers()
            .subscribeBy(
                onSuccess = {
                    onUpdateCharacters(it.characters)
                    onUpdatePagination(it.currentCount, it.paginationOffset)
                },
                onError = {
                    onError(it)
                }
            )
            .also { addDisposable(it) }
    }

    override fun onUpdateFavorite(characterViewObject: CharacterViewObject) {

        val isFavorite = characterViewObject.isFavorite.not()
        characterViewObject.isFavorite = isFavorite
        val entity = mapper.toEntity(characterViewObject)
        val name = characterViewObject.name
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
            .subscribe { view?.showToast(messageId, name) }
            .also { addDisposable(it) }
    }

    override fun getParameters(query: String?): GetCharactersParams {
        val params = GetCharactersParams()
        params.offset = paginationOffset
        params.query = query
        return params
    }

    override fun onUpdateCharacters(characters: List<CharacterEntity>) {
        if (characters.isNotEmpty()) {
            val viewObjectList = mapper.toViewObjectList(characters)
            view?.showCharacters(viewObjectList)
        } else {
            view?.showEmptyState()
        }
    }

    override fun onUpdatePagination(currentCount: Int, offset: Int) {
        paginationOffset = offset + currentCount
    }

    override fun onError(it: Throwable) {
        view?.showMessage(getErrorMessage(it))
    }

    private fun getErrorMessage(error: Throwable) =
        when (error) {
            is NetworkError.NotConnected -> R.string.message_no_internet
            is NetworkError.SlowConnection -> R.string.message_slow_internet
            is NetworkError.Canceled -> R.string.message_unknown_error
            else -> R.string.message_unknown_error
        }
}

