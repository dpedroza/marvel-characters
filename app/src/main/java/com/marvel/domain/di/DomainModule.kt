package com.marvel.domain.di

import com.marvel.domain.model.CharacterEntity
import com.marvel.domain.model.GetCharactersParams
import com.marvel.domain.model.GetCharactersResultEntity
import com.marvel.domain.repository.CharactersRepository
import com.marvel.domain.repository.FavoriteRepository
import com.marvel.domain.usecase.GetCharacters
import com.marvel.domain.usecase.GetFavorites
import com.marvel.domain.usecase.UpdateFavorite
import com.marvel.domain.usecase.UseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetCharactersUseCase(characterRepository: CharactersRepository): UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResultEntity> {
        return GetCharacters(characterRepository)
    }

    @Provides
    fun provideGetFavoritesUseCase(favoriteRepository: FavoriteRepository): UseCase.FromSingle.WithoutInput<List<CharacterEntity>> {
        return GetFavorites(favoriteRepository)
    }

    @Provides
    fun provideUpdateFavoriteUseCase(favoriteRepository: FavoriteRepository): UseCase.FromCompletable.WithInput<CharacterEntity> {
        return UpdateFavorite(favoriteRepository)
    }
}