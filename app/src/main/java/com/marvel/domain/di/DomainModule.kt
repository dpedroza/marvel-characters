package com.marvel.domain.di

import com.marvel.domain.repository.CharactersRepository
import com.marvel.domain.repository.FavoriteRepository
import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.GetFavorites
import com.marvel.domain.usecase.UpdateFavorite
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetCharactersUseCase(characterRepository: CharactersRepository): GetCharacters {
        return GetCharacters(characterRepository)
    }

    @Provides
    fun provideGetFavoritesUseCase(favoriteRepository: FavoriteRepository): GetFavorites {
        return GetFavorites(favoriteRepository)
    }

    @Provides
    fun provideUpdateFavoriteUseCase(favoriteRepository: FavoriteRepository): UpdateFavorite {
        return UpdateFavorite(favoriteRepository)
    }
}