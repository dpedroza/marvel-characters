package com.marvel.domain.di

import com.marvel.domain.CharactersRepository
import com.marvel.domain.GetCharactersUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetCharactersUseCase(characterRepository: CharactersRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }
}