package com.marvel.domain.di

import com.marvel.domain.model.entity.CharacterEntity
import com.marvel.domain.model.params.GetCharactersParams
import com.marvel.domain.model.result.GetCharactersResult
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
    fun provideGetCharactersUseCase(
        characterRepository: CharactersRepository
    ): UseCase.FromSingle.WithInput<GetCharactersParams, GetCharactersResult> {
        return GetCharacters(characterRepository)
    }

    @Provides
    fun provideGetFavoritesUseCase(
        favoriteRepository: FavoriteRepository
    ): UseCase.FromSingle.WithoutInput<List<CharacterEntity>> {
        return GetFavorites(favoriteRepository)
    }

    @Provides
    fun provideUpdateFavoriteUseCase(
        favoriteRepository: FavoriteRepository
    ): UseCase.FromCompletable.WithInput<CharacterEntity> {
        return UpdateFavorite(favoriteRepository)
    }
}
