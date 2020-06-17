package com.marvel.presentation.ui.characters

import com.marvel.R
import com.marvel.domain.GetCharactersUseCase
import javax.inject.Inject

class CharacterPresenter @Inject constructor(val useCase: GetCharactersUseCase) :
    CharactersContract.Presenter() {

    override fun loadCharacters() {
        useCase.execute(
            onSuccess = { view?.showCharacters(emptyList()) },
            onError = { view?.showMessage(R.string.heroes) }
        )
    }
}

