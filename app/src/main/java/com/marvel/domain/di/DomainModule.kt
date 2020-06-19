package com.marvel.domain.di

import com.marvel.domain.repository.CharactersRepository
import com.marvel.domain.repository.FavoriteRepository
import com.marvel.domain.usecase.AddFavorite
import com.marvel.domain.usecase.GetCharacters
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetCharactersUseCase(characterRepository: CharactersRepository): GetCharacters {
        return GetCharacters(characterRepository)
    }

    @Provides
    fun provideAddFavoriteUseCase(favoriteRepository: FavoriteRepository): AddFavorite {
        return AddFavorite(favoriteRepository)
    }
}