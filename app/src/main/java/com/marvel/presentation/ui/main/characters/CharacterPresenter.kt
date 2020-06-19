package com.marvel.presentation.ui.main.characters

import com.marvel.R
import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParameters
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.domain.usecase.GetCharacters
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.CharacterViewObject
import com.marvel.presentation.model.PaginationData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterPresenter @Inject constructor(
    private val updateFavorite: UpdateFavorite,
    private val getCharacters: GetCharacters,
    private val mapper: ViewObjectMapper
) : CharactersContract.Presenter() {

    private var pagination = PaginationData()

    override fun loadCharacters(
        query: String,
        reset: Boolean
    ) {
        if (pagination.isFinalPage) return
        if (reset) pagination.clear()

        view?.showLoading()

        GetCharactersParameters(pagination.offset, query)
            .apply {
                getCharacters.execute(this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result ->
                            view?.hideLoading()
                            view?.showCharacters(mapper.transform(result), reset)
                            updatePagination(
                                result.currentCount,
                                result.totalCount,
                                result.paginationOffset
                            )
                        },
                        {
                            view?.hideLoading()
                            view?.showMessage(R.string.heroes)
                        }
                    )
                    .also { addDisposable(it) }
            }
    }

    override fun updateFavorite(characterViewObject: CharacterViewObject) {
        CharacterEntity(
            id = characterViewObject.id,
            name = characterViewObject.name,
            imageUrl = characterViewObject.bannerURL,
            isFavorite = characterViewObject.isFavorite
        ).also { entity ->
            updateFavorite.execute(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
                .also { addDisposable(it) }
        }
    }

    private fun updatePagination(currentCount: Int, totalCount: Int, offset: Int) {
        pagination.isFinalPage = currentCount == totalCount
        pagination.offset = offset + currentCount
    }
}

