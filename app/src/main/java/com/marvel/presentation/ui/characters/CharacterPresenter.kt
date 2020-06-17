package com.marvel.presentation.ui.characters

import com.marvel.R
import com.marvel.domain.usecase.GetCharacters
import com.marvel.presentation.model.CharacterViewObject
import javax.inject.Inject

data class PaginationData(
    var hasNextPage: Boolean = true,
    var offset: Int = 0
)

class CharacterPresenter @Inject constructor(private val useCase: GetCharacters) :
    CharactersContract.Presenter() {

    var paginationData = PaginationData()

    override fun loadCharacters(query: String) {

        if (!paginationData.hasNextPage) return

        view?.showLoading()
        useCase.execute(
            offset = paginationData.offset,
            query = query,
            onSuccess = {

                paginationData.hasNextPage = it.data.count != it.data.total
                paginationData.offset = it.data.offset + it.data.count

                val characters = mutableListOf<CharacterViewObject>()
                for (character in it.data.results) {
                    val url =
                        "${character.thumbnail.path}.${character.thumbnail.extension}".replaceFirst(
                            "http",
                            "https"
                        )
                    val viewObject = CharacterViewObject(character.name, url)
                    characters.add(viewObject)
                }
                view?.showCharacters(characters)
                view?.hideLoading()
            },
            onError = {
                view?.showMessage(R.string.heroes)
                view?.hideLoading()
            }
        )
    }
}

