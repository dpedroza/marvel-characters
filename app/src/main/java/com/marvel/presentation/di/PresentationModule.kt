package com.marvel.presentation.di

import com.marvel.domain.GetCharactersUseCase
import com.marvel.presentation.ui.characters.CharacterPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PerFragment
    @Provides
    fun providesCharacterPresenter(getCharactersUseCase: GetCharactersUseCase): CharacterPresenter {
        return CharacterPresenter(getCharactersUseCase)
    }
}