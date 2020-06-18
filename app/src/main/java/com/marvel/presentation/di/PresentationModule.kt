package com.marvel.presentation.di

import com.marvel.domain.usecase.GetCharacters
import com.marvel.presentation.mapper.ViewObjectMapper
import com.marvel.presentation.ui.characters.CharacterPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @PerFragment
    @Provides
    fun providesCharacterPresenter(
        getCharacters: GetCharacters,
        mapper: ViewObjectMapper
    ): CharacterPresenter {
        return CharacterPresenter(getCharacters, mapper)
    }

    @PerFragment
    @Provides
    fun providesViewObjectMapper(): ViewObjectMapper {
        return ViewObjectMapper()
    }
}
