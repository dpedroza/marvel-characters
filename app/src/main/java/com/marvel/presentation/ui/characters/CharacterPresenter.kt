package com.marvel.presentation.ui.characters

import com.marvel.R
import com.marvel.domain.usecase.GetCharacters
import com.marvel.presentation.model.CharacterViewObject
import javax.inject.Inject

class CharacterPresenter @Inject constructor(private val useCase: GetCharacters) :
    CharactersContract.Presenter() {

    override fun loadCharacters() {
        view?.showLoading()
        useCase.execute(
            query = "",
            onSuccess = {
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

