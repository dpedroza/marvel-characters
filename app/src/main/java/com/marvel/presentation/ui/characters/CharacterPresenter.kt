package com.marvel.presentation.ui.characters

import com.marvel.R
import com.marvel.domain.usecase.GetCharacters
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.model.PaginationData
import javax.inject.Inject

class CharacterPresenter @Inject constructor(
    private val getCharacters: GetCharacters,
    private val mapper: ViewObjectMapper
) : CharactersContract.Presenter() {

    private var pagination = PaginationData()

    override fun loadCharacters(
        query: String,
        clear: Boolean
    ) {

        if (pagination.isFinalPage) return
        if (clear) pagination.clear()

        view?.showLoading()

        getCharacters.execute(
            offset = pagination.offset,
            query = query,
            onSuccess = {

                updatePagination(
                    currentCount = it.currentCount,
                    totalCount = it.totalCount,
                    offset = it.paginationOffset
                )

                view?.showCharacters(
                    characters = mapper.transform(it),
                    clear = clear
                )
                view?.hideLoading()
            },
            onError = {
                view?.showMessage(R.string.heroes)
                view?.hideLoading()
            }
        )
    }

    private fun updatePagination(currentCount: Int, totalCount: Int, offset: Int) {

        pagination.isFinalPage = currentCount == totalCount
        pagination.offset = offset + currentCount
    }
}

