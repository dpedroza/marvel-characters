package com.marvel.presentation.di

import com.marvel.domain.usecase.GetCharacters
import com.marvel.presentation.ui.characters.CharacterPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PerFragment
    @Provides
    fun providesCharacterPresenter(getCharacters: GetCharacters): CharacterPresenter {
        return CharacterPresenter(getCharacters)
    }
}